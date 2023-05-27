package com.gm.gym_manager_web.entity;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.gm.gym_manager_web.entity.base.GoodsBase;
import jakarta.persistence.*;
import lombok.Data;
import org.apache.ibatis.annotations.One;

import java.sql.Date;

/**
 * @author 陈桢梁
 * @desc OrderDataEntity.java
 * @date 2023-05-09 22:20
 * @logs[0] 2023-05-09 22:20 陈桢梁 创建了OrderDataEntity.java文件
 */
@Entity(name = "order_data")
@Table
@Data
public class OrderDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int orderId;

    private Date date;

    private int number;

    private int start;

    private int end;

    @JsonAlias({"gym_id"})
    @JsonProperty("gym_id")
    @Column(name = "gym_id")
    private int gymId;

    @OneToOne()
    @JoinColumn(name = "goods_id", referencedColumnName = "id")
    private GoodsBase goods;

}
