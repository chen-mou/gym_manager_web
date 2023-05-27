package com.gm.gym_manager_web.dao.jpa;

import com.gm.gym_manager_web.entity.OrderDataEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.sql.Time;
import java.util.List;

/**
 * @author 陈桢梁
 * @desc OrderDataRepository.java
 * @date 2023-05-09 23:06
 * @logs[0] 2023-05-09 23:06 陈桢梁 创建了OrderDataRepository.java文件
 */
@Repository
public interface OrderDataRepository extends CrudRepository<OrderDataEntity, Integer> {

    @Query(value = "(select * from order_data od where ?1 between od.start and od.end and od.date = ?3 limit 1) " +
            "union all " +
            "(select * from order_data od where ?2 between od.start and od.end and od.date = ?3 limit 1)", nativeQuery = true)
    List<OrderDataEntity> findTime( int start,int end,Date time);


    @Query(value = "select number - " +
            "(select COALESCE(sum(od.number), 0) as number " +
            "from order_data od where od.goods_id = ?1 and od.date = ?2) as number " +
            "from equipment where id = ?1", nativeQuery = true)
    int findNumber(int goodsId, Date date);

}
