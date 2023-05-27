package com.gm.gym_manager_web.service;

import com.gm.gym_manager_web.dao.jpa.GymRepository;
import com.gm.gym_manager_web.dao.mybaits.GymDao;
import com.gm.gym_manager_web.entity.GymEntity;
import com.gm.gym_manager_web.entity.vo.GymVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 陈桢梁
 * @desc GymService.java
 * @date 2023-05-09 15:44
 * @logs[0] 2023-05-09 15:44 陈桢梁 创建了GymService.java文件
 */
@Service
public class GymService {

    @Resource
    GymDao gymDao;

    @Resource
    GymRepository gymRepository;

    public List<GymVo> getNear(float lon, float lat, int page){
        int offset = (page - 1) * 20;
        return gymDao.getNear(lon, lat, offset, 20);
    }

    public GymEntity getGymById(int id){
        return gymRepository.findById(id).get();
    }

}
