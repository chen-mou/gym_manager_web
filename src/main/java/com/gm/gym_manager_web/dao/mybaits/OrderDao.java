package com.gm.gym_manager_web.dao.mybaits;

import com.gm.gym_manager_web.entity.OrderEntity;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Update;

/**
 * @author 陈桢梁
 * @desc OrderDao.java
 * @date 2023-05-10 15:46
 * @logs[0] 2023-05-10 15:46 陈桢梁 创建了OrderDao.java文件
 */
@Mapper
public interface OrderDao {

    @Insert("insert into orders(count, user_id, status)value(#{count}, #{user.id}, #{status})")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(OrderEntity order);

    @Update("update orders set status = #{status} where id = #{id}")
    void update(OrderEntity.Status status, int id);
}
