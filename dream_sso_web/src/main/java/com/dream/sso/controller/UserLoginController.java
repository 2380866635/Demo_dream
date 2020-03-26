package com.dream.sso.controller;

import com.alibaba.dubbo.common.json.JSON;
import com.dream.common.pojo.CookieUtils;
import com.dream.common.pojo.DreamResult;
import com.dream.sso.service.UserLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.lang.management.MemoryType;

@Controller
public class UserLoginController {
    @Autowired
    private UserLoginService userLoginService;
    @Value("${DREAM_TOKEN_KEY}")
    private String DREAM_TOKEN_KEY;
    @RequestMapping(value = "/user/login",method = RequestMethod.POST)
    @ResponseBody
    public DreamResult login(String username, String password,
                             HttpServletResponse response, HttpServletRequest request){
            //登录
        DreamResult longin = userLoginService.longin(username, password);
            //如果登录成功 需要把coken 加入到cookie中 返回给浏览器
        if (longin.getStatus()==200){
                //把token加入到cookie:DREAM_TOKEN_KEY=longin.getData().toString();

            CookieUtils.setCookie(request,response,DREAM_TOKEN_KEY,longin.getData().toString());
        }
        return longin;
    }

    @RequestMapping(value = "/user/token/{token}",method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public String getUserByToken(@PathVariable String token,String callback) throws IOException {
        DreamResult userByToken = userLoginService.getUserByToken(token);
        if (StringUtils.isNoneBlank(callback)){ //有callback代表jsonp跨域请求
            //返回一个callback从成立  也是一个json
            String jsonResult = callback + "(" + JSON.json(userByToken) + ");";
            return  jsonResult;
        }
        return JSON.json(userByToken);
    }

}
