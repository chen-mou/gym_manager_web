package com.gm.gym_manager_web.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gm.gym_manager_web.entity.OrderDataEntity;
import lombok.Data;

/**
 * @author 陈桢梁
 * @desc OrderDataGymVo.java
 * @date 2023-05-18 00:59
 * @logs[0] 2023-05-18 00:59 陈桢梁 创建了OrderDataGymVo.java文件
 */
@Data
public class OrderDataGymVo extends OrderDataEntity {

    @JsonProperty("name")
    String gymName;

}
