package com.gm.gym_manager_web.entity.vo;

import com.gm.gym_manager_web.entity.EquipmentEntity;
import com.gm.gym_manager_web.entity.base.GoodsBase;
import lombok.Data;

/**
 * @author 陈桢梁
 * @desc EquipmentVo.java
 * @date 2023-05-26 15:53
 * @logs[0] 2023-05-26 15:53 陈桢梁 创建了GoodsVo.java文件
 */
@Data
public class EquipmentVo extends EquipmentEntity {

    public EquipmentVo(EquipmentEntity base){
        this.setAvatar(base.getAvatar());
        this.setGymId(base.getGymId());
        this.setId(base.getId());
        this.setName(base.getName());
        this.setNumber(base.getNumber());
        this.setPrice(base.getPrice());
        this.setUnit(base.getUnit());
        this.setType(base.getType());
    }

    private int total;

}
