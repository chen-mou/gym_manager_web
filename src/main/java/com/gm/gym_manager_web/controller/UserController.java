package com.gm.gym_manager_web.controller;

import com.gm.gym_manager_web.entity.vo.RegisterVo;
import com.gm.gym_manager_web.service.UserService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.hibernate.service.spi.ServiceException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tool.Result;

import java.util.stream.Collectors;

/**
 * @author 陈桢梁
 * @desc UserController.java
 * @date 2023-05-09 01:30
 * @logs[0] 2023-05-09 01:30 陈桢梁 创建了UserController.java文件
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    UserService service;

    @PostMapping("/register")
    public Result Register(@Valid @RequestBody RegisterVo registerVo, Errors errors){
        if(errors.hasErrors()){
            throw new RuntimeException(errors.
                    getAllErrors().
                    stream().
                    map((err) -> err.getDefaultMessage()).
                    collect(Collectors.joining("\n")));
        }
        return Result.success(service.register(registerVo));
    }

    @PostMapping("/login")
    public Result login(@Valid @RequestBody RegisterVo registerVo, Errors errors){
        if(errors.hasErrors()){
            throw new RuntimeException(errors.
                    getAllErrors().
                    stream().
                    map((err) -> err.getDefaultMessage()).
                    collect(Collectors.joining("\n")));
        }
        return Result.success(service.login(registerVo));
    }


    @PostMapping("/work_login")
    public Result workLogin(@Valid @RequestBody RegisterVo registerVo, Errors errors){
        if(errors.hasErrors()){
            throw new RuntimeException(errors.
                    getAllErrors().
                    stream().
                    map((err) -> err.getDefaultMessage()).
                    collect(Collectors.joining("\n")));
        }
        return Result.success(service.workLogin(registerVo));
    }
}
