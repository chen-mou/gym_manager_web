package com.gm.gym_manager_web.dao.mybaits;

import com.gm.gym_manager_web.entity.EquipmentEntity;
import com.gm.gym_manager_web.entity.OrderDataEntity;
import com.gm.gym_manager_web.entity.VenueEntity;
import com.gm.gym_manager_web.entity.base.GoodsBase;
import org.apache.ibatis.annotations.*;

import java.util.Date;
import java.util.List;

/**
 * @author 陈桢梁
 * @desc GoodsDao.java
 * @date 2023-05-10 13:53
 * @logs[0] 2023-05-10 13:53 陈桢梁 创建了GoodsDao.java文件
 */
@Mapper
public interface GoodsDao {

    @Select("select od.* from order_data od " +
            "left join orders o on od.order_id = o.id " +
            "where od.date = #{date} and o.status != 0 and od.goods_id = #{goodsId}")
    List<OrderDataEntity> getDataByDate(int goodsId, Date date);



    @Select("select * from goods g " +
            "left join venue v on g.id = v.id and g.type = 'Venue' " +
            "left join equipment e on g.id = e.id and g.type = 'Equipment' " +
            "where g.id = #{id}")
    @TypeDiscriminator(column = "type", cases = {
            @Case(type = EquipmentEntity.class, value = "Equipment"),
            @Case(type = VenueEntity.class, value = "Venue")
    })
    @Results(id = "goods",
            value = {
                    @Result(column = "gym_id", property = "gymId")
            }
    )
    GoodsBase getById(int id);

    @Select("<script>" +
            "select 1 from Equipment where id in(" +
            "<foreach collection='id' separator=',' item='item'>" +
                "#{item}" +
            "</foreach>" +
            ") for update" +
            "</script>")
    void lockByIds(List<Integer> ids);

    @Update("update equipment set number = number - #{number} where id = #{id} and number >= #{number}")
    int updateEquipmentNumber(int id, int number);

}
