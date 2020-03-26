package com.dream.sso.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.dream.common.pojo.DreamResult;
import com.dream.mapper.TbUserMapper;
import com.dream.pojo.TbUser;
import com.dream.pojo.TbUserExample;
import com.dream.sso.jedis.JedisClient;
import com.dream.sso.service.UserRegisterService;
import org.apache.commons.lang3.StringUtils;
import org.omg.PortableInterceptor.USER_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserRegisterServiceImpl implements UserRegisterService {
  @Autowired
  private TbUserMapper tbUserMapper;
  @Autowired
  private JedisClient jedisClient;
  @Value("${EXPIRED_TIME}")
  private Integer EXPIRED_TIME;
  @Value("${USER_INFO}")
  private String USER_INFO;
    @Override
    public DreamResult checkUserInfo(String param, Integer type) {
       //1、type的不同需要设置不同的条件
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        //2、设置查询参数  1-username  2phone 3eamil
        if(type==1){ //检查是否是 username
            //username不能为空 前端已经表单验证检测过了 后面也继续做厝里
            if(StringUtils.isEmpty(param)){//这个是判断如果是空
                return DreamResult.ok(false);
            }//如果不是空
            criteria.andUsernameEqualTo(param);
        }else  if(type==2){//如果是2-phone号码
            criteria.andPhoneEqualTo(param);
        }else if (type==3){//如果是邮箱登录
            criteria.andEmailEqualTo(param);
        }else {
            return DreamResult.build(400,"请输入正确登录数据！");
        }
        //3执行查询
        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
        //4如果查询到的数据了 注册账号不可用已经存在返回 False
        if(tbUsers!=null&&tbUsers.size()>0){
            return  DreamResult.ok(false);
        }
        //5 如果没有查询到数据说明账号可用
        return DreamResult.ok(true);
    }

    /**
     * 注册信息验证
     * @param tbUser
     * @return
     */
    @Override
    public DreamResult register(TbUser tbUser) {
       //1、检验注册数据
        //1.1、用户名和账户不能为空
        if (StringUtils.isEmpty(tbUser.getUsername())
                ||StringUtils.isEmpty(tbUser.getPassword())){
            return  DreamResult.build(400,"注册失败！账户和密码不能为空！");
        }
        //1.2、校验用户名是否已经存在了
        DreamResult dreamResult = checkUserInfo(tbUser.getUsername(), 1);
            if (!(boolean)dreamResult.getData()){
                return  DreamResult.build(400,"注册失败！账户已存在！");
            }
            //1.3手机号是否已经注册
        if(StringUtils.isNoneBlank(tbUser.getPhone())) {
            DreamResult dreamResult1 = checkUserInfo(tbUser.getPhone(), 2);
            if (!(boolean) dreamResult1.getData()) {
                return DreamResult.build(400, "注册失败！账户已存在！");
            }
        }
        //1.4邮箱号是否已经注册
        if(StringUtils.isNoneBlank(tbUser.getEmail())) {
            DreamResult dreamResult1 = checkUserInfo(tbUser.getEmail(), 3);
            if (!(boolean) dreamResult1.getData()) {
                return DreamResult.build(400, "注册失败！账户已存在！");
            }
        }
        //2、如果效验通过，补全其他属性 id是自增的 还有两个创建时间和修改时间
        tbUser.setCreated(new Date());
        tbUser.setUpdated(tbUser.getCreated());
        //3对密码进行加密  这里使用的是重新产生个字符串b
        String password = DigestUtils.md5DigestAsHex(tbUser.getPassword().getBytes());
        tbUser.setPassword(password);
        //4、插入数据
        tbUserMapper.insertSelective(tbUser);
        return DreamResult.ok();
    }


}
