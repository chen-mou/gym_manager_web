package com.gm.gym_manager_web.entity.vo;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.gm.gym_manager_web.entity.ReserveEntity;
import lombok.Data;

/**
 * @author 陈桢梁
 * @desc ReserveGymVo.java
 * @date 2023-05-17 22:50
 * @logs[0] 2023-05-17 22:50 陈桢梁 创建了ReserveGymVo.java文件
 */
@Data
public class ReserveGymVo extends ReserveEntity {
    @JsonProperty("gym_name")
    String gymName;
}
