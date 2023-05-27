package com.gm.gym_manager_web.dao.jpa;

import com.gm.gym_manager_web.entity.ReserveEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;
/**
 * @author 陈桢梁
 * @desc ReserveRepository.java
 * @date 2023-05-09 22:28
 * @logs[0] 2023-05-09 22:28 陈桢梁 创建了ReserveRepository.java文件
 */
@Repository
public interface ReserveRepository extends CrudRepository<ReserveEntity, Integer> {

//    List<ReserveEntity> findReserveEntitiesByUserIdAndDateGreaterThanOrDateEqualsAndStartGreaterThan(int userId, Date now, int start);

    ReserveEntity findByStartAndEndAndGoodsIdAndUserIdAndDate(int start, int end, int goodsId, int userId, Date date);

}
