package com.gm.gym_manager_web.entity;

import jakarta.persistence.*;
import lombok.Data;
import tool.enums.Role;

/**
 * @author 陈桢梁
 * @desc UserEntity.java
 * @date 2023-05-08 23:04
 * @logs[0] 2023-05-08 23:04 陈桢梁 创建了UserEntity.java文件
 */
@Entity(name = "user")
@Table
@Data
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String password;

    private String nickname;

    private Role role;

    private String avatar;

}
