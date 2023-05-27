package com.gm.gym_manager_web.catcher;

import org.hibernate.service.spi.ServiceException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import tool.Result;

/**
 * @author 陈桢梁
 * @desc GlobeCatcher.java
 * @date 2023-05-09 00:55
 * @logs[0] 2023-05-09 00:55 陈桢梁 创建了GlobeCatcher.java文件
 */
@RestControllerAdvice
public class GlobeCatcher {

    @ExceptionHandler(Exception.class)
    public Result exception(Exception e){
        e.printStackTrace();
        return Result.fail("服务器出错:" + e.getMessage());
    }

}
