package com.dream.order.interceptor;

import com.dream.common.pojo.CookieUtils;
import com.dream.common.pojo.DreamResult;
import com.dream.pojo.TbUser;
import com.dream.sso.service.UserLoginService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor{
   @Value("${COOKIE_TOKEN_KEY}")
   private String COOKIE_TOKEN_KEY;
   @Value("${SSO_URL}")
    private String SSO_URL;
    @Autowired
    private UserLoginService userLoginService;
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
       //先从cookie中获取token
        String token = CookieUtils.getCookieValue(request, COOKIE_TOKEN_KEY);
        /**
         * 如果没有token直接跳转到sso登录界面  而且还要把当前请求url参数传递给sso系统
            当用户登陆过后直接跳回到该路景
            需要在请求的url中添加一个重定向
         */
        if (StringUtils.isBlank(token)){
            //跳转到sso登录界面 并把单签拦截操作的url传过去  登录完后重定向
            String url=SSO_URL+"/page/login?redirect"+request.getRequestURI().toString();

            System.out.println("这是地址"+request.getRequestURI().toString());
            //重定向
            response.sendRedirect(url);
            return false;
        }
        //如果有token  需要调用 sso服务 根据token查询用户信息
        DreamResult result = userLoginService.getUserByToken(token);
        TbUser user=null;
        //如果找到了
        if (result!=null&&result.getStatus()==200){
            user = (TbUser) result.getData();
        }else {
            //有token但是登录过期了  也去登录界面
            //跳转到sso登录界面 并把单签拦截操作的url传过去  登录完后重定向
            String url=SSO_URL+"/page/login?redirect"+request.getRequestURI().toString();
            //重定向
            response.sendRedirect(url);
            return false;
        }
        //把查到的用户绑定到request中 直接传给controller中  要不然在cotroller中还要调用service查询
        request.setAttribute("user",user);
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
                           ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
