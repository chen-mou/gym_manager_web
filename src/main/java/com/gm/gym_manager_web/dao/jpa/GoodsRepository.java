package com.gm.gym_manager_web.dao.jpa;

import com.gm.gym_manager_web.entity.base.GoodsBase;
import org.springframework.data.repository.CrudRepository;
import java.util.List;

/**
 * @author 陈桢梁
 * @desc VenueRepository.java
 * @date 2023-05-09 16:05
 * @logs[0] 2023-05-09 16:05 陈桢梁 创建了VenueRepository.java文件
 */
public interface GoodsRepository extends CrudRepository<GoodsBase, Integer> {

    List<GoodsBase> findAllByGymIdAndTypeAndStatusNot(int gymId, String type, GoodsBase.Status status);

}
