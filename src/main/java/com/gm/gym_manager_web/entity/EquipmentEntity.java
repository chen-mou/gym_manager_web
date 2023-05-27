package com.gm.gym_manager_web.entity;

import com.gm.gym_manager_web.entity.base.GoodsBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈桢梁
 * @desc EquipmentEntity.java
 * @date 2023-05-09 00:19
 * @logs[0] 2023-05-09 00:19 陈桢梁 创建了EquipmentEntity.java文件
 */
@Entity(name = "equipment")
@Table(name = "equipment")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EquipmentEntity extends GoodsBase {

    public EquipmentEntity(int id,
                           String type,
                           float price,
                           String unit,
                           String avatar,
                           String name,
                           int gymId,int number){
        super(id, price, unit, avatar, name, type, gymId, Status.Normal);
        this.number = number;
    }

    private int number;

}
