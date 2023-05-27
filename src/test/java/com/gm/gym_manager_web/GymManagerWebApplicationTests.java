package com.gm.gym_manager_web;

import com.gm.gym_manager_web.dao.jpa.GoodsRepository;
import com.gm.gym_manager_web.dao.jpa.OrderDataRepository;
import com.gm.gym_manager_web.dao.mybaits.GoodsDao;
import com.gm.gym_manager_web.dao.mybaits.ReserveDao;
import com.gm.gym_manager_web.entity.EquipmentEntity;
import com.gm.gym_manager_web.entity.ReserveEntity;
import com.gm.gym_manager_web.entity.VenueEntity;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;

@SpringBootTest
class GymManagerWebApplicationTests {

//    @Resource
//    GoodsRepository goodsRepository;
//
//    @Resource
//    ReserveDao reserveDao;
//
//    @Resource
//    OrderDataRepository orderDataRepository;
//
//    @Value("${server.url_prefix}")
//    String prefix;
//    @Test
//    @Transactional
//    void contextLoads() {
//        VenueEntity venue = new VenueEntity(1, "venue", 12, "元/小时",
//                prefix + "/default.jpg", "篮球场", 1, "篮球场");
//        EquipmentEntity equipmentEntity = new EquipmentEntity(1, "venue", 12, "元/小时/个",
//                prefix + "/default.jpg", "篮球场", 1,20);
//        for (int i = 0;i < 10;i++){
//            if (i % 2 == 0){
//                goodsRepository.save(venue);
//            }else{
//                goodsRepository.save(equipmentEntity);
//            }
//        }
//    }

//    @Test
//    void testGet(){
//        System.out.println(orderDataRepository.findNumber(38, new Date(new java.util.Date().getTime())));
//    }

}
