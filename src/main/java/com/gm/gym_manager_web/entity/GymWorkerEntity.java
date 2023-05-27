package com.gm.gym_manager_web.entity;

import jakarta.persistence.*;
import lombok.Data;

/**
 * @author 陈桢梁
 * @desc GymWorker.java
 * @date 2023-05-09 14:49
 * @logs[0] 2023-05-09 14:49 陈桢梁 创建了GymWorker.java文件
 */
@Entity(name = "gym_worker")
@Table
@Data
public class GymWorkerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;

    @Column(name = "gym_id")
    private int gymId;

}
