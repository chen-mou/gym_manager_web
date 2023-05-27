package com.gm.gym_manager_web.entity;

import com.gm.gym_manager_web.entity.base.GoodsBase;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Time;
import java.util.List;

/**
 * @author 陈桢梁
 * @desc GymEntity.java
 * @date 2023-05-08 23:12
 * @logs[0] 2023-05-08 23:12 陈桢梁 创建了GymEntity.java文件
 */
@Entity(name = "gym")
@Table
@Data
public class GymEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private float longitude;

    private float latitude;

    private String avatar;

    private String address;

    private String description;

    private Time open;

    private Time end;

    private String tel;

    @OneToMany(mappedBy = "gymId", fetch = FetchType.LAZY)
    private List<GoodsBase> goods;

    @OneToMany(mappedBy = "gymId")
    private List<GymWorkerEntity> workers;


}
