package com.gm.gym_manager_web.controller;

import com.gm.gym_manager_web.entity.ReserveEntity;
import com.gm.gym_manager_web.entity.UserEntity;
import com.gm.gym_manager_web.entity.vo.ReserveVo;
import com.gm.gym_manager_web.service.ReserveService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tool.Result;
import tool.SessionUtil;
import tool.annotation.Permission;
import tool.annotation.UserInfo;
import tool.enums.Role;

import java.sql.Date;
import java.util.stream.Collectors;


/**
 * @author 陈桢梁
 * @desc ReserveController.java
 * @date 2023-05-09 01:31
 * @logs[0] 2023-05-09 01:31 陈桢梁 创建了ReserveController.java文件
 */
@RestController
@RequestMapping("/reserve")
public class ReserveController {

    @Resource
    ReserveService reserveService;

    @PostMapping("/save")
    @Permission(Role.Custom)
    public Result save(@Valid @RequestBody ReserveVo reserve, Errors errors){
        if(errors.hasErrors()){
            return Result.fail(errors.getAllErrors().stream().map(item -> item.getDefaultMessage()).collect(Collectors.joining("\n")));
        }
        if(reserve.getEnd() <= reserve.getStart()){
            return Result.fail("结束时间不能小于开始时间");
        }
        ReserveEntity res = reserveService.save(reserve, SessionUtil.getCurrentUser().getId());
        return Result.success(res);
    }

    @GetMapping("/myReserve")
    @Permission(Role.Custom)
    public Result getByUserId(@RequestParam(name = "gym_id") int gymId, @UserInfo UserEntity user){
        return Result.success(reserveService.myReserve(user.getId(), gymId));
    }

    @GetMapping("/myAllReserve")
    @Permission(Role.Custom)
    public Result getAllByUserId(@UserInfo UserEntity user){
        return Result.success(reserveService.myAllReserve(user.getId()));
    }

}
