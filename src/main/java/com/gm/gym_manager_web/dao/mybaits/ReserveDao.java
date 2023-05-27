package com.gm.gym_manager_web.dao.mybaits;

import com.gm.gym_manager_web.entity.ReserveEntity;
import com.gm.gym_manager_web.entity.vo.ReserveGymVo;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.util.List;
import java.util.Map;

/**
 * @author 陈桢梁
 * @desc ReserveDao.java
 * @date 2023-05-11 17:09
 * @logs[0] 2023-05-11 17:09 陈桢梁 创建了ReserveDao.java文件
 */
@Mapper
public interface ReserveDao {

    @Select("<script>" +
            "select * from reserve left join user u on u.id = reserve.user_id where reserve.id in(" +
            "<foreach collection='id' separator=',' item='item'>" +
                "#{item}" +
            "</foreach>" +
            ")" +
            "</script>")
    @Results(id = "reserve", value = {
            @Result(column = "goods_id", property = "goods",
                    one = @One(select = "com.gm.gym_manager_web.dao.mybaits.GoodsDao.getById")),
            @Result(column = "user_id", property = "user.id"),
            @Result(column = "gym_id", property = "gymId")
    })
    List<ReserveEntity> getAllById(List<Integer> id);

    @Select("select * from reserve r " +
            "where r.user_id = #{userId} and " +
            "(r.date > #{date} or (r.date = #{date} and r.start > HOUR(CURRENT_TIME()))) " +
            "and gym_id = #{gymId}")
    @ResultMap("reserve")
    List<ReserveEntity> getByUserId(int userId, int gymId, Date date);


    @Select("select g.name, r.* from reserve r " +
            "left join gym g on g.id = r.gym_id " +
            "where r.user_id = #{userId} and " +
            "(r.date > CURRENT_TIME() or (r.date = CURRENT_TIME() and r.start > HOUR(CURRENT_TIME())))")
    @Results(value = {
            @Result(column = "name", property = "gymName"),
            @Result(column = "goods_id", property = "goods",
                    one = @One(select = "com.gm.gym_manager_web.dao.mybaits.GoodsDao.getById"))
    })
    List<ReserveGymVo> getAllByUserId(int userId);
}
