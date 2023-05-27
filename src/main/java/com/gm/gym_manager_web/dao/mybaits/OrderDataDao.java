package com.gm.gym_manager_web.dao.mybaits;

import com.gm.gym_manager_web.entity.OrderDataEntity;
import com.gm.gym_manager_web.entity.vo.OrderDataGymVo;
import org.apache.ibatis.annotations.*;

import java.sql.Date;
import java.sql.Time;
import java.util.*;
/**
 * @author 陈桢梁
 * @desc OrderDataDao.java
 * @date 2023-05-10 15:03
 * @logs[0] 2023-05-10 15:03 陈桢梁 创建了OrderDataDao.java文件
 */
@Mapper
public interface OrderDataDao {

    @Select("select * from order_data for update")
    List<OrderDataEntity> lock();

    @Insert("<script>" +
            " insert into order_data(number, goods_id, order_id, start, end, date, gym_id)" +
            " value" +
            " <foreach item='order' separator=',' collection='orders'>" +
            "   (#{order.number}, #{order.goods.id}, #{order.orderId}, #{order.start}, #{order.end}, #{order.date}, #{order.gymId})" +
            " </foreach>" +
            "</script>")
    @Options(useGeneratedKeys = true, keyProperty = "id", keyColumn = "id")
    void insert(List<OrderDataEntity> orders);


    @Select("select * from order_data od left join orders o on od.order_id = o.id " +
            " where date = #{time} and o.status != 0 " +
            " and (#{start} between start and end or #{end} between start and end) limit 1")
    OrderDataEntity verifyTime(int start, int end, Date time);


    @Select("select number - (select COALESCE(sum(od.number), 0) as number "+
    "from order_data od where od.goods_id = #{goodsId} and od.date = #{date}) as number from equipment where id = #{goodsId}")
    int getEquipmentRemainder(int goodsId, Date date);

    @Select("select g.name, od.* from order_data od " +
            "left join orders o on od.order_id = o.id " +
            "left join gym g on g.id = od.gym_id " +
            "where o.user_id = #{userId} and status = 2")
    @Results(value = {
            @Result(column = "name", property = "gymName"),
            @Result(property = "goods", column = "goods_id", one = @One(select = "com.gm.gym_manager_web.dao.mybaits.GoodsDao.getById"))
    })
    List<OrderDataGymVo> getAllUserOrderData(int userId);

    @Select("select od.* from order_data od " +
            "left join goods g on g.id = od.goods_id " +
            "left join orders o on o.id = od.order_id " +
            "where g.type = #{type} and od.date = #{date} and o.status = 2")
    @Result(column = "goods_id", property = "goods",
            one = @One(select = "com.gm.gym_manager_web.dao.mybaits.GoodsDao.getById"))
    List<OrderDataEntity> getGymAllReserveByDataAndType(int gymId, Date date, String type);
}
