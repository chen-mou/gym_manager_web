package com.gm.gym_manager_web.Interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * @author 陈桢梁
 * @desc CrossInterceptor.java
 * @date 2022-04-08 12:59
 * @logs[0] 2022-04-08 12:59 陈桢梁 创建了CrossInterceptor.java文件
 */
//设置跨域请求头
public class CrossInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler){
        res.setHeader("Access-Control-Allow-Methods", "PUT, GET, POST, DELETE, OPTIONS");
        res.setHeader("Access-Control-Allow-Headers", "Origin, X-Requested-With, Content-Type, Accept, Authorization, Content-Length, Cookie, Access-Control-Allow-Credentials, Token");
        res.setHeader("Access-Control-Allow-Credentials", "true");
        res.setHeader("Access-Control-Allow-Origin",req.getHeader("Origin"));
        return true;
    }
}
