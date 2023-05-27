package com.gm.gym_manager_web.Interceptor;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gm.gym_manager_web.entity.UserEntity;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import tool.Result;
import tool.SessionUtil;
import tool.annotation.Permission;
import tool.enums.Role;

import java.io.IOException;

/**
 * @author 陈桢梁
 * @desc AuthFilter.java
 * @date 2023-05-09 00:59
 * @logs[0] 2023-05-09 00:59 陈桢梁 创建了AuthFilter.java文件
 */
@Slf4j
public class AuthInterceptor implements HandlerInterceptor {

//    private Logger logger = Context.app.getBean(Logger.class);

    private void fail(HttpServletResponse res, String msg) {
        ObjectMapper om = new ObjectMapper();
        try {
            byte[] value = om.writeValueAsBytes(Result.fail(msg));
            res.setContentType("application/json");
            res.getOutputStream().write(value);
        }catch (JsonProcessingException e){
            log.error(e.getMessage());
        } catch (IOException e) {
            log.error(e.getMessage());
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response,
                             Object handler) {
        if(handler instanceof HandlerMethod){
            SessionUtil.setSession(request.getSession());
            HandlerMethod methodHandler = (HandlerMethod) handler;
            Permission permission = methodHandler.getMethod().getAnnotation(Permission.class);
            if(permission == null){
                return true;
            }
            UserEntity now = SessionUtil.getCurrentUser();
            if(now == null){
                fail(response, "未登录");
                return false;
            }
            if(permission.value() == Role.Login){
                return true;
            }
            if(!now.getRole().equals(permission.value())){
                fail(response, "没有权限");
                return false;
            }
            return true;
        }
        return true;
    }
}
