package com.gm.gym_manager_web.dao.jpa;

import com.gm.gym_manager_web.entity.GymEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 陈桢梁
 * @desc GymRepository.java
 * @date 2023-05-08 23:31
 * @logs[0] 2023-05-08 23:31 陈桢梁 创建了GymRepository.java文件
 */
@Repository
public interface GymRepository extends CrudRepository<GymEntity, Integer> {
}
