package com.dream.sso.service;

import com.dream.common.pojo.DreamResult;
import com.dream.pojo.TbUser;

public interface UserRegisterService {
    /**
     * 检测账户是否可用
     * @param param
     * @param type 类型 1-username 2 phone 3email
     * @return
     */
    DreamResult checkUserInfo(String param,Integer type);

    /**
     * 注册信息验证
     * @param tbUser
     * @return
     */
    DreamResult register(TbUser tbUser);


}
