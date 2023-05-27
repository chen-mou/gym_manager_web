package com.gm.gym_manager_web.service;

import com.gm.gym_manager_web.dao.jpa.GoodsRepository;
import com.gm.gym_manager_web.dao.jpa.GymWorkerRepository;
import com.gm.gym_manager_web.dao.jpa.OrderDataRepository;
import com.gm.gym_manager_web.dao.mybaits.GoodsDao;
import com.gm.gym_manager_web.entity.EquipmentEntity;
import com.gm.gym_manager_web.entity.GymWorkerEntity;
import com.gm.gym_manager_web.entity.OrderDataEntity;
import com.gm.gym_manager_web.entity.base.GoodsBase;
import com.gm.gym_manager_web.entity.vo.EquipmentVo;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 陈桢梁
 * @desc GoodsService.java
 * @date 2023-05-09 16:24
 * @logs[0] 2023-05-09 16:24 陈桢梁 创建了GoodsService.java文件
 */
@Service
public class GoodsService {

    @Resource
    GoodsRepository goodsRepository;

    @Resource
    GymWorkerRepository workerRepository;

    @Resource
    OrderDataRepository orderDataRepository;

    @Resource
    GoodsDao goodsDao;

    @Value("${server.url_prefix}")
    String urlPrefix;

    public GoodsBase save(GoodsBase base, int userId){
        GymWorkerEntity worker = workerRepository.findGymWorkerByUserId(userId);
        if(worker == null){
            throw new RuntimeException("你没有这个权限");
        }
        base.setGymId(worker.getGymId());
        base.setStatus(GoodsBase.Status.Normal);
        if(base.getAvatar().equals("")) {
            base.setAvatar(urlPrefix + "/default.jpg");
        }
        goodsRepository.save(base);
        return base;
    }

    @Transactional
    public void delete(int id){
       Optional<GoodsBase> gb = goodsRepository.findById(id);
       if(gb != null){
           gb.get().setStatus(GoodsBase.Status.Delete);
       }
    }

    public List<Map.Entry<Integer, Integer>> getFreeTimeByDate(int goods, Date date){
        List<OrderDataEntity> orderData = goodsDao.getDataByDate(goods, date);
        List<Map.Entry<Integer, Integer>> res = new LinkedList<>();
        List<Map.Entry<Integer, Integer>> temp = new LinkedList<>();
        res.add(Map.entry(0, 24));
        for (OrderDataEntity item : orderData) {
            while (!res.isEmpty()) {
                Map.Entry<Integer, Integer> data = res.remove(0);
                if (data.getKey() < item.getStart() && data.getValue() > item.getEnd()) {
                    temp.add(Map.entry(data.getKey(), item.getStart()));
                    temp.add(Map.entry(item.getEnd(), data.getValue()));
                } else {
                    temp.add(data);
                }
            }
            List<Map.Entry<Integer, Integer>> mid = res;
            res = temp;
            temp = mid;
            temp.clear();
        }
        return res;
    }


    public void updateGood(int goodId, int num){
        goodsDao.updateEquipmentNumber(goodId, -num);
    }


    public List<GoodsBase> getByGymAndType(String type, int gymId){
        List<GoodsBase> bases = goodsRepository.findAllByGymIdAndTypeAndStatusNot(gymId, type, GoodsBase.Status.Delete);
        if(type.equals("Equipment")){
            Date now = new Date(Calendar.getInstance().getTimeInMillis());
            bases = bases.stream().map(item -> {
                EquipmentEntity eq = ((EquipmentEntity)item);
                EquipmentVo vo = new EquipmentVo(eq);
                vo.setTotal(eq.getNumber());
                vo.setNumber(orderDataRepository.findNumber(item.getId(), now));
                return vo;
            }).collect(Collectors.toList());
        }
        return bases;
    }


    public List<GoodsBase> getByWorkerAndType(int userId, String type){
        GymWorkerEntity worker = workerRepository.findGymWorkerByUserId(userId);
        if(worker == null){
            throw new RuntimeException("工作人员不存在");
        }
        return getByGymAndType(type, worker.getGymId());
    }

    public int getNowRemainder(int goodsId, Date date){
        return orderDataRepository.findNumber(goodsId, date);
    }

}
