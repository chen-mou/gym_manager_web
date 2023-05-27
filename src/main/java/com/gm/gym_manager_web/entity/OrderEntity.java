package com.gm.gym_manager_web.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

/**
 * @author 陈桢梁
 * @desc OrderEntity.java
 * @date 2023-05-09 13:35
 * @logs[0] 2023-05-09 13:35 陈桢梁 创建了OrderEntity.java文件
 */
@Entity(name = "orders")
@Data
@Table
public class OrderEntity {

    public enum Status {
        EXPIRE,
        NO_PAY,
        PAY,
        COMPLETE
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double count;

    private Status status;

    @OneToOne()
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @OneToMany(mappedBy = "orderId")
    private List<OrderDataEntity> orderDatas;
}
