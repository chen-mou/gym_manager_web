package com.gm.gym_manager_web.service;

import com.gm.gym_manager_web.dao.jpa.GymWorkerRepository;
import com.gm.gym_manager_web.dao.jpa.OrderDataRepository;
import com.gm.gym_manager_web.dao.jpa.ReserveRepository;
import com.gm.gym_manager_web.dao.mybaits.GoodsDao;
import com.gm.gym_manager_web.dao.mybaits.OrderDao;
import com.gm.gym_manager_web.dao.mybaits.OrderDataDao;
import com.gm.gym_manager_web.dao.mybaits.ReserveDao;
import com.gm.gym_manager_web.entity.*;
import com.gm.gym_manager_web.entity.vo.AllOrderDataVo;
import com.gm.gym_manager_web.entity.vo.OrderDataGymVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 陈桢梁
 * @desc OrderService.java
 * @date 2023-05-10 15:00
 * @logs[0] 2023-05-10 15:00 陈桢梁 创建了OrderService.java文件
 */
@Service
public class OrderService {


    @Resource
    ReserveRepository reserveRepository;

    @Resource
    OrderDataDao orderDataDao;

    @Resource
    OrderDao orderDao;

    @Resource
    GoodsDao goodsDao;

    @Resource
    GymWorkerRepository gymWorkerRepository;
    @Resource
    ReserveDao reserveDao;

    @Transactional(rollbackFor = {Exception.class})
    public OrderEntity createOrder(List<Integer> ids, int userId){
        List<ReserveEntity> reserves = reserveDao.getAllById(ids);
        List<OrderDataEntity> value = new LinkedList<>();
        double count = 0;
        Calendar c = Calendar.getInstance();
        long now = c.getTimeInMillis() / (24 * 1000 * 60 * 60);
        orderDataDao.lock();
        for (ReserveEntity item : reserves) {
            long date = (long) Math.ceil(item.getDate().getTime() * 1.0 / (24 * 1000 * 60 * 60));
            if(now > date || (now == date && item.getStart() < c.get(Calendar.HOUR_OF_DAY))){
                throw new RuntimeException("不能预约以前的时间");
            }
            OrderDataEntity orderData = orderDataDao.verifyTime(item.getStart(), item.getEnd(), item.getDate());
            if(item.getGoods().getType().equals("Venue")){
                if (orderData != null) {
                    throw new RuntimeException("预约时间冲突");
                }
            }
            if(item.getGoods().getType().equals("Equipment")){
                if(item.getNumber() > orderDataDao.getEquipmentRemainder(item.getGoods().getId(), item.getDate())){
                    throw new RuntimeException("器材数量大于剩余数量");
                }
            }
            count += item.getGoods().getPrice() * item.getNumber() * (item.getEnd() - item.getStart());
        }
        OrderEntity order = new OrderEntity();
        UserEntity user = new UserEntity();
        user.setId(userId);
        order.setUser(user);
        order.setCount(count);
        order.setStatus(OrderEntity.Status.NO_PAY);
        orderDao.insert(order);
        reserves.forEach(item -> {
            OrderDataEntity data = new OrderDataEntity();
            data.setStart(item.getStart());
            data.setEnd(item.getEnd());
            data.setGoods(item.getGoods());
            data.setNumber(item.getNumber());
            data.setDate(item.getDate());
            data.setOrderId(order.getId());
            data.setGymId(item.getGymId());
            value.add(data);
//            if(item.getGoods().getType().equals("Equipment")){
//                int rowNum = goodsDao.updateEquipmentNumber(item.getGoods().getId(), item.getNumber());
//                if(rowNum == 0){
//                    throw new RuntimeException("器材被预约完了");
//                }
//            }
        });
        orderDataDao.insert(value);
        return order;
    }


    public void pay(int orderId){
        orderDao.update(OrderEntity.Status.PAY, orderId);
    }


    public List<AllOrderDataVo> myAllReserve(int userId){
        List<OrderDataGymVo> orderDataGymVos = orderDataDao.getAllUserOrderData(userId);
        List<AllOrderDataVo> res = new ArrayList<>();
        orderDataGymVos.
                stream().
                collect(Collectors.groupingBy((item) -> item.getGymName())).
                forEach((k, v) -> {
                    AllOrderDataVo vo = new AllOrderDataVo();
            vo.setReserve(v.stream().collect(Collectors.toList()));
            vo.setName(k);
            res.add(vo);
        });
        return res;
    }
//    public Map<Integer, OrderDataEntity>

    public List<OrderDataEntity> getGymReserve(int userId, Date date, String type){
        GymWorkerEntity worker = gymWorkerRepository.findGymWorkerByUserId(userId);
        if(worker == null){
            throw new RuntimeException("你没有权限");
        }
        return orderDataDao.getGymAllReserveByDataAndType(worker.getGymId(), date, type);
    }
}
