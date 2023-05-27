package com.gm.gym_manager_web.dao.jpa;

import com.gm.gym_manager_web.entity.GymWorkerEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 陈桢梁
 * @desc GymWorkerRepository.java
 * @date 2023-05-09 16:29
 * @logs[0] 2023-05-09 16:29 陈桢梁 创建了GymWorkerRepository.java文件
 */
@Repository
public interface GymWorkerRepository extends CrudRepository<GymWorkerEntity, Integer> {

    GymWorkerEntity findGymWorkerByUserId(int userId);

}
