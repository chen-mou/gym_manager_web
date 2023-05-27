package com.gm.gym_manager_web.controller;

import com.gm.gym_manager_web.entity.UserEntity;
import com.gm.gym_manager_web.service.OrderService;
import jakarta.annotation.Resource;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;
import tool.Result;
import tool.annotation.JsonKey;
import tool.annotation.Permission;
import tool.annotation.UserInfo;
import tool.enums.Role;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author 陈桢梁
 * @desc OrderController.java
 * @date 2023-05-09 22:17
 * @logs[0] 2023-05-09 22:17 陈桢梁 创建了OrderController.java文件
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    OrderService orderService;

    @PostMapping("/create")
    @Permission(Role.Custom)
    public Result create(@JsonKey(name = "reserve_ids") List<Integer> reserveIds, @UserInfo UserEntity user){
        return Result.success(orderService.createOrder(reserveIds, user.getId()));
    }

    @PostMapping("/pay")
    @Permission(Role.Custom)
    public Result pay(@JsonKey(name = "id") int id){
        orderService.pay(id);
        return Result.success("成功");
    }

    @GetMapping("/myPayOrder")
    @Permission(Role.Custom)
    public Result myPayOrder(@UserInfo UserEntity user){
        return Result.success(orderService.myAllReserve(user.getId()));
    }

    @GetMapping("/getGymReserve")
    @Permission(Role.Worker)
    public Result gymReserve(@RequestParam(name = "date") String dateStr,
                             @RequestParam String type,
                             @UserInfo UserEntity user) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date date = new Date(sdf.parse(dateStr).getTime());
        return Result.success(orderService.getGymReserve(user.getId(), date, type));

    }

}
