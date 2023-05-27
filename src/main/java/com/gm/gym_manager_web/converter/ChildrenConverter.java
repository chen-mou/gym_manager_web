package com.gm.gym_manager_web.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.hibernate.service.spi.ServiceException;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import tool.IOUtil;
import tool.annotation.Base;

import java.io.IOException;
import java.io.Reader;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.nio.charset.StandardCharsets;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

/**
 * @author 陈桢梁
 * @desc ChildrenConverter.java
 * @date 2023-05-09 17:44
 * @logs[0] 2023-05-09 17:44 陈桢梁 创建了ChildrenConverter.java文件
 */
public class ChildrenConverter implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(Base.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Base b = parameter.getParameterAnnotation(Base.class);
        Map<String, Object> param = getParam(webRequest, b.type());
        return setObject(b, param);
    }

    private Map<String, Object> getParam(NativeWebRequest webRequest, Base.ParamType type) throws IOException {
        Object obj = webRequest.getNativeRequest();
        if(obj instanceof HttpServletRequest){
            HttpServletRequest request = (HttpServletRequest) obj;
            String data = IOUtil.readAll(request.getReader());
            switch (type){
                case Form:
                case Query:
                    throw new RuntimeException("暂时未实现");
                case JSON:
                    ObjectMapper om = new ObjectMapper();
                    return om.readValue(data.getBytes(StandardCharsets.UTF_8), Map.class);
                default:
                    throw new ServerException("暂时未实现");
            }
        }
        throw new ServerException("");
    }


    private Object setObject(Base base, Map<String, Object> param) throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        String field = (String) param.getOrDefault(base.field(), "");
        Class c;
        try {
            c = Class.forName(base.packages() + "." + base.prefix() + field + base.suffix());
        }catch (ClassNotFoundException e){
            throw new RuntimeException("不存在目标类");
        }
        Object res = c.getDeclaredConstructor().newInstance();
        ArrayList<Field> fields = new ArrayList<>();
        while (c != null){
            fields.addAll(Arrays.asList(c.getDeclaredFields()));
            c = c.getSuperclass();
        }
        fields.forEach((item) -> {
            item.setAccessible(true);
            try {
                Object value = param.get(item.getName());
                if(value == null){
                    return;
                }
                item.set(res, value);
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
        return res;
    }

}
