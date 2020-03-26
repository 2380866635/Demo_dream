package com.dream.sso.service;

import com.dream.common.pojo.DreamResult;

public interface UserLoginService {
    /**
     * 登录功能
     * @param username
     * @param password
     * @return
     */
    DreamResult longin(String username, String password);

    /**
     * 单点登录获取
     * @param token
     * @return
     */
    DreamResult getUserByToken(String token);
}
