package com.gm.gym_manager_web.dao.mybaits;

import com.gm.gym_manager_web.entity.GymEntity;
import com.gm.gym_manager_web.entity.vo.GymVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author 陈桢梁
 * @desc GymDao.java
 * @date 2023-05-08 23:17
 * @logs[0] 2023-05-08 23:17 陈桢梁 创建了GymDao.java文件
 */
@Mapper
public interface GymDao {

    @Select("select * from (select *," +
            " round((st_distance_sphere(point(longitude, latitude)," +
            " point(#{lon}, #{lat})))/1000, 2) as distance " +
            "from gym where end > now()) temp where distance < 50 " +
            " order by distance limit #{offset}, #{limit}")
    List<GymVo> getNear(float lon, float lat, int offset, int limit);

}
