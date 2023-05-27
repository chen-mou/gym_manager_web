package com.gm.gym_manager_web.entity;

import com.gm.gym_manager_web.entity.base.GoodsBase;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 陈桢梁
 * @desc VenueEntity.java
 * @date 2023-05-09 00:09
 * @logs[0] 2023-05-09 00:09 陈桢梁 创建了VenueEntity.java文件
 */
@Entity(name = "venue")
@Table(name = "venue")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class VenueEntity extends GoodsBase {

    public VenueEntity(int id,
                       String type,
                       float price,
                       String unit,
                       String avatar,
                       String name,
                       int gymId, String venueType){
        super(id, price, unit, avatar, name, type, gymId, Status.Normal);
        this.venueType = venueType;
    }

    @Column(name = "type")
    private String venueType;
}
