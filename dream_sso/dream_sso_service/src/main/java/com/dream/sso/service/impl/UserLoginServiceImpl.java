package com.dream.sso.service.impl;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.dubbo.common.json.JSON;
import com.alibaba.dubbo.common.json.ParseException;
import com.dream.common.pojo.DreamResult;
import com.dream.mapper.TbUserMapper;
import com.dream.pojo.TbUser;
import com.dream.pojo.TbUserExample;
import com.dream.sso.jedis.JedisClient;
import com.dream.sso.service.UserLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

import java.io.IOException;
import java.util.List;
import java.util.UUID;
@Service
public class UserLoginServiceImpl implements UserLoginService {

    @Autowired
    private TbUserMapper tbUserMapper;
    @Autowired
    private JedisClient jedisClient;
    @Value("${EXPIRED_TIME}")
    private Integer EXPIRED_TIME;
    @Value("${USER_INFO}")
    private String USER_INFO;
    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    @Override
    public DreamResult longin(String username, String password) {
        //1、检验账户或密码是否为空
        if(StringUtils.isEmpty(username)||StringUtils.isEmpty(password)){
            return DreamResult.build(400,"用户或密码不能为空！");
        }
        //2、校验用户登录信息
        TbUserExample tbUserExample = new TbUserExample();
        TbUserExample.Criteria criteria = tbUserExample.createCriteria();
        //根据email登录
        /*criteria.andEmailEqualTo(username);
        criteria.andPhoneEqualTo(username);*/
        criteria.andUsernameEqualTo(username);
        List<TbUser> tbUsers = tbUserMapper.selectByExample(tbUserExample);
        if(tbUsers==null||tbUsers.size()==0){
            return DreamResult.build(400,"用户不存在！");
        }
        //3 在校验密码  用户名是否唯一
        TbUser tbUser = tbUsers.get(0);
        //进行密码的比
        String s = DigestUtils.md5DigestAsHex(password.getBytes());
        if (!s.equals(tbUser.getPassword())){
            return  DreamResult.build(400,"账户或密码错误！");
        }
        //4如果校验成功 账户和密码正确 登录成功
        //需要把登录信息保存到redis中 作为模拟的session  每一个用户的登录token必须唯一
        //4.1随机生成一个token
        String token = UUID.randomUUID().toString();
        //4.2.把要保存的用户 要將信息输出到前端的不能有密码
        tbUser.setPassword(null);
        //4.3将用户存放到redis中 使用jedis 客户端
        try {
            jedisClient.set(USER_INFO+":"+token, JSON.json(tbUser));
        } catch (IOException e) {
            e.printStackTrace();
        }
        //设置过期时间
        jedisClient.expire(USER_INFO+":"+token,EXPIRED_TIME);
        //点击登录其他系统如果要从 redis中查看是否用登录信息  需要一个此时的token session放到cookiezhong
        return DreamResult.ok(token);
    }


    public DreamResult getUserByToken(String token) {
        //1直接根据token从redis中进行查询
        String json = jedisClient.get(USER_INFO + ":" + token);
        //2判断是否查到
        if(StringUtils.isNoneBlank(json)){
            //3每一次重新访问首页 实际上都是重新登录状态计时
            jedisClient.expire(USER_INFO+":"+token,EXPIRED_TIME);
            try {
                TbUser tbUser = JSON.parse(json, TbUser.class);
                //返回对象
                return  DreamResult.ok(tbUser);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return DreamResult.build(400,"用户已经过期");
    }
}
