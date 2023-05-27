package com.gm.gym_manager_web.catcher;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import tool.Result;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author 陈桢梁
 * @desc ResponseBodyCatcher.java
 * @date 2023-05-15 00:47
 * @logs[0] 2023-05-15 00:47 陈桢梁 创建了ResponseBodyCatcher.java文件
 */
@ControllerAdvice
@Slf4j
public class ResponseBodyCatcher implements ResponseBodyAdvice<Result> {

    final ObjectMapper om = new ObjectMapper();

    final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return returnType.getParameterType().equals(Result.class);
    }

    @Override
    public Result beforeBodyWrite(Result body,
                                  MethodParameter returnType,
                                  MediaType selectedContentType,
                                  Class<? extends HttpMessageConverter<?>> selectedConverterType,
                                  ServerHttpRequest request,
                                  ServerHttpResponse response) {
        String result;
        try {
            result = om.writeValueAsString(body);
            log.info(request.getMethod().name() + " " + request.getURI().getPath() + " " + result);
            return body;
        } catch (JsonProcessingException e) {
            log.info("序列化出错");
            return body;
        }

    }
}
