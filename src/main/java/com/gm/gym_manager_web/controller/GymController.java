package com.gm.gym_manager_web.controller;

import com.gm.gym_manager_web.entity.GymEntity;
import com.gm.gym_manager_web.service.GymService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import tool.Result;

/**
 * @author 陈桢梁
 * @desc GymController.java
 * @date 2023-05-09 00:51
 * @logs[0] 2023-05-09 00:51 陈桢梁 创建了GymController.java文件
 */
@RequestMapping("/gym")
@RestController
public class GymController {

    @Resource
    GymService gymService;

    @GetMapping("/near")
    public Result Near(float longitude, float latitude, int page){
        return Result.success(gymService.getNear(longitude, latitude, page));
    }

    @GetMapping("/{id}")
    public Result getById(@PathVariable int id){
        return Result.success(gymService.getGymById(id));
    }

    @PostMapping("/create")
    public Result create(@RequestBody GymEntity gymEntity){
        return Result.fail("未完成");
    }

}
