package com.gm.gym_manager_web.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gm.gym_manager_web.entity.base.GoodsBase;
import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

/**
 * @author 陈桢梁
 * @desc ReserveEntity.java
 * @date 2023-05-08 23:38
 * @logs[0] 2023-05-08 23:38 陈桢梁 创建了ReserveEntity.java文件
 */
@Table(name = "reserve")
@Entity(name = "reserve")
@Data
public class ReserveEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int start;

    private int end;

    private int number;
//    @Column(name = "venue_id")
//    private int venueId;
//
//    @Column(name = "equipment_id")
//    private int equipmentId;
    private Date date;

    @JsonAlias({"gym_id"})
    @JsonProperty("gym_id")
    @Column(name = "gym_id")
    private int gymId;

    @OneToOne
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    private GoodsBase goods;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;





}
