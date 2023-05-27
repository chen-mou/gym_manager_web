package com.gm.gym_manager_web.dao.jpa;

import com.gm.gym_manager_web.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 * @author 陈桢梁
 * @desc User.java
 * @date 2023-05-08 23:28
 * @logs[0] 2023-05-08 23:28 陈桢梁 创建了User.java文件
 */
@Repository
public interface UserRepository extends CrudRepository<UserEntity, Integer> {

    UserEntity findByName(String name);

}
