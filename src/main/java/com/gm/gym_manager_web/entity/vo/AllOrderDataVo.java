package com.gm.gym_manager_web.entity.vo;

import com.gm.gym_manager_web.entity.OrderDataEntity;
import lombok.Data;

import java.util.List;

/**
 * @author 陈桢梁
 * @desc AllOrderDataVo.java
 * @date 2023-05-18 01:24
 * @logs[0] 2023-05-18 01:24 陈桢梁 创建了AllOrderDataVo.java文件
 */
@Data
public class AllOrderDataVo {
    private String name;

    List<OrderDataEntity> reserve;
}
