package com.gm.gym_manager_web.controller;

import com.gm.gym_manager_web.entity.UserEntity;
import com.gm.gym_manager_web.entity.base.GoodsBase;
import com.gm.gym_manager_web.service.FileService;
import com.gm.gym_manager_web.service.GoodsService;
import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import org.hibernate.service.spi.ServiceException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import tool.Result;
import tool.annotation.Base;
import tool.annotation.JsonKey;
import tool.annotation.Permission;
import tool.annotation.UserInfo;
import tool.enums.Role;

import java.sql.Date;
import java.util.stream.Collectors;

/**
 * @author 陈桢梁
 * @desc GoodsController.java
 * @date 2023-05-09 01:32
 * @logs[0] 2023-05-09 01:32 陈桢梁 创建了GoodsController.java文件
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {


    @Resource
    GoodsService goodsService;

    @Resource
    FileService fileService;

    @PostMapping("/save")
    @Permission(Role.Worker)
    public Result save(@Base(type = Base.ParamType.JSON,
            field = "type",
            suffix = "Entity",
            packages = "com.gm.gym_manager_web.entity") GoodsBase base,
                       @UserInfo UserEntity user){
        if(base.getType() == null || base.getType().isBlank()){
            throw new RuntimeException("type不能为空");
        }
        if(!base.getType().equals("Venue") && !base.getType().equals("Equipment")){
            throw new RuntimeException("type不是合法参数");
        }
        goodsService.save(base, user.getId());
        return Result.success(base);
    }


    @PostMapping("/delete")
    @Permission(Role.Worker)
    public Result delete(@JsonKey int id){
        goodsService.delete(id);
        return Result.success("成功");
    }

    @GetMapping("/freeTime")
    public Result freeTime(@RequestParam int goodsId,
                           @RequestParam Date time){
        return Result.success(goodsService.getFreeTimeByDate(goodsId, time));
    }

    @PostMapping("/backEquipment")
    public Result backEquipment(@RequestParam int id, @RequestParam int number){
        if(number < 0){
            return Result.fail("归还数量不能小于0");
        }
        goodsService.updateGood(id, number);
        return Result.success("成功");
    }

    @GetMapping("/getByGymAndType")
    public Result getByGymAndType(@RequestParam String type, @RequestParam(name = "gym_id") int gym){
        return Result.success(goodsService.getByGymAndType(type, gym));
    }
    @GetMapping("/getByWorkerAndType")
    @Permission(Role.Worker)
    public Result getByWorkerAndType(@RequestParam String type, @UserInfo UserEntity user){
        return Result.success(goodsService.getByWorkerAndType(user.getId(), type));
    }

    @GetMapping("/nowRemainder")
    public Result getNowRemainder(@RequestParam(name = "goods_id") int goodsId, Date date){
        return Result.success(goodsService.getNowRemainder(goodsId, date));
    }

    @PostMapping("/uploadAvatar")
    @Permission(Role.Worker)
    public Result UploadAvatar(@RequestParam MultipartFile file){
        return Result.success(fileService.save(file));
    }

}
