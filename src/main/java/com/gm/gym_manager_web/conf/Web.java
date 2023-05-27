package com.gm.gym_manager_web.conf;

import com.gm.gym_manager_web.Interceptor.AuthInterceptor;
import com.gm.gym_manager_web.Interceptor.CrossInterceptor;
import com.gm.gym_manager_web.converter.ChildrenConverter;
import com.gm.gym_manager_web.converter.JsonConverter;
import com.gm.gym_manager_web.converter.UserInfoConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * @author 陈桢梁
 * @desc Mvc.java
 * @date 2023-05-09 14:19
 * @logs[0] 2023-05-09 14:19 陈桢梁 创建了Mvc.java文件
 */
@Configuration
public class Web implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        WebMvcConfigurer.super.addInterceptors(registry);
        registry.addInterceptor(new CrossInterceptor());
        registry.addInterceptor(new AuthInterceptor());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        WebMvcConfigurer.super.addArgumentResolvers(resolvers);
        resolvers.add(new UserInfoConverter());
        resolvers.add(new ChildrenConverter());
        resolvers.add(new JsonConverter());
    }

}
