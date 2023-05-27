package com.gm.gym_manager_web.converter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;
import tool.IOUtil;
import tool.annotation.JsonKey;

import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author 陈桢梁
 * @desc JsonConverter.java
 * @date 2023-05-11 16:25
 * @logs[0] 2023-05-11 16:25 陈桢梁 创建了JsonConverter.java文件
 */
public class JsonConverter implements HandlerMethodArgumentResolver {
    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return parameter.getParameterAnnotation(JsonKey.class) != null;
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer, NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        Map<String, Object> map = (Map<String, Object>) webRequest.getAttribute("json_value", RequestAttributes.SCOPE_REQUEST);
        if(map == null){
            HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
            String st = IOUtil.readAll(request.getReader());
            ObjectMapper om = new ObjectMapper();
            map = om.readValue(st.getBytes(StandardCharsets.UTF_8), Map.class);
            webRequest.setAttribute("json_value", map, RequestAttributes.SCOPE_REQUEST);
        }
        String name = parameter.getParameterAnnotation(JsonKey.class).name();
        if(name.equals("")){
            name = parameter.getParameterName();
        }
        return map.get(name);
    }
}
