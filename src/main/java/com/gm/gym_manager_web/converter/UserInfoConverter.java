package com.gm.gym_manager_web.converter;

import com.gm.gym_manager_web.entity.UserEntity;
import org.hibernate.service.spi.ServiceException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import tool.annotation.UserInfo;

/**
 * @author 陈桢梁
 * @desc UserInfoConverter.java
 * @date 2022-04-08 14:21
 * @logs[0] 2022-04-08 14:21 陈桢梁 创建了UserInfoConverter.java文件
 */
public class UserInfoConverter implements HandlerMethodArgumentResolver {
    //判断当前参数是否需要使用消息转换器
    @Override
    public boolean supportsParameter(MethodParameter methodParameter) {
//        System.out.println(methodParameter.getParameterAnnotation(UserInfo.class));
        return methodParameter.getParameterAnnotation(UserInfo.class) != null;
    }

    //将结果作为当前参数的值
    @Override
    public Object resolveArgument(MethodParameter methodParameter,
                                  ModelAndViewContainer modelAndViewContainer,
                                  NativeWebRequest nativeWebRequest,
                                  WebDataBinderFactory webDataBinderFactory) {
        UserEntity user = (UserEntity) nativeWebRequest.getAttribute("user", RequestAttributes.SCOPE_SESSION);
        if(user == null){
            throw new RuntimeException("未登录");
        }
//        System.out.println(user);
        return user;
    }
}
