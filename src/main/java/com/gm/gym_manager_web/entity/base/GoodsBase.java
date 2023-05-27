package com.gm.gym_manager_web.entity.base;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonKey;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.core.serializer.Serializer;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

/**
 * @author 陈桢梁
 * @desc GoodsBase.java
 * @date 2023-05-09 00:04
 * @logs[0] 2023-05-09 00:04 陈桢梁 创建了GoodsBase.java文件
 */
@Data
@Entity(name = "goods")
@Inheritance(strategy = InheritanceType.JOINED)
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
public class GoodsBase {

    public enum Status{
        Normal,Delete
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private double price;

    @Column(length = 8)
    private String unit;

    private String avatar;

    @Column(length = 32)
    private String name;

    //取值Venue或Equipment
    @NotNull
    private String type;

    @Column(name = "gym_id")
    @JsonAlias({"gym_id"})
    @JsonProperty("gym_id")
    private int gymId;

    private Status status;

}
