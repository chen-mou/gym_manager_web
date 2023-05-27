package com.gm.gym_manager_web.service;

import com.gm.gym_manager_web.dao.jpa.*;
import com.gm.gym_manager_web.dao.mybaits.ReserveDao;
import com.gm.gym_manager_web.entity.*;
import com.gm.gym_manager_web.entity.base.GoodsBase;
import com.gm.gym_manager_web.entity.vo.AllReserveVo;
import com.gm.gym_manager_web.entity.vo.GymVo;
import com.gm.gym_manager_web.entity.vo.ReserveGymVo;
import com.gm.gym_manager_web.entity.vo.ReserveVo;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author 陈桢梁
 * @desc ReserveService.java
 * @date 2023-05-09 22:29
 * @logs[0] 2023-05-09 22:29 陈桢梁 创建了ReserveService.java文件
 */
@Service
public class ReserveService {

    @Resource
    GoodsRepository goodsRepository;

    @Resource
    GymRepository gymRepository;

    @Resource
    OrderDataRepository orderDataRepository;

    @Resource
    ReserveRepository reserveRepository;

    @Resource
    ReserveDao reserveDao;

    @Resource
    GymWorkerRepository gymWorkerRepository;

    @Transactional
    public ReserveEntity save(ReserveVo vo, int userId){
        GoodsBase base = verify(vo);
        if(vo.getId() != null){
            if(reserveRepository.findById(vo.getId()).get().getUser().getId() != userId){
                throw new RuntimeException("你没有权限修改别人的购物车");
            }
        }
        ReserveEntity reserve = reserveRepository.findByStartAndEndAndGoodsIdAndUserIdAndDate(vo.getStart(), vo.getEnd(),
                vo.getGoodsId(), userId,vo.getToday());
        if(reserve != null){
            if(reserve.getGoods().getType().equals("Venue")){
                throw new RuntimeException("不能重复预定场馆");
            }
            reserve.setNumber(reserve.getNumber() + vo.getNumber());
            int number = orderDataRepository.findNumber(vo.getGoodsId(), vo.getToday());
            if(number < reserve.getNumber()){
                throw new RuntimeException("所选器材个数大于剩余个数");
            }
        }else{
            reserve = new ReserveEntity();
            reserve.setStart(vo.getStart());
            reserve.setEnd(vo.getEnd());
            UserEntity user = new UserEntity();
            user.setId(userId);
            reserve.setUser(user);
            reserve.setGoods(base);
            reserve.setDate(vo.getToday());
            reserve.setGymId(vo.getGymId());
            reserve.setNumber(vo.getNumber());
        }
        reserveRepository.save(reserve);
        return reserve;
    }

    public GoodsBase verify(ReserveVo vo) {
        Optional<GoodsBase> op = goodsRepository.findById(vo.getGoodsId());
        if(op.isEmpty()){
            throw new RuntimeException("商品不存在");
        }
        GoodsBase base = op.get();
        GymEntity gym = gymRepository.findById(base.getGymId()).get();
        switch (base.getType()){
            case "Venues":
                List<OrderDataEntity> orderDataEntity = orderDataRepository.findTime(vo.getStart(), vo.getEnd(), vo.getToday());
                if (orderDataEntity.size() > 0){
                    throw new RuntimeException("预约时间冲突");
                }
                if(vo.getNumber() > 1){
                    throw new RuntimeException("场馆数量不能大于1");
                }
                break;
            case "Equipment":
                int number = orderDataRepository.findNumber(vo.getGoodsId(), vo.getToday());
                if(number < vo.getNumber()){
                    throw new RuntimeException("器材数量大于剩余数量");
                }
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(gym.getEnd());
        if(vo.getEnd() > calendar.get(Calendar.HOUR_OF_DAY)){
            throw new RuntimeException("所选时间太晚了");
        }
        return base;
    }

    public List<ReserveEntity> myReserve(int userId, int gymId){
        Calendar c = Calendar.getInstance();
        int start = c.get(Calendar.HOUR_OF_DAY);
        return reserveDao.getByUserId(userId, gymId, new Date(c.getTimeInMillis()));
    }

    public List<AllReserveVo> myAllReserve(int userId){
        List<ReserveGymVo> reserveGymVos = reserveDao.getAllByUserId(userId);
        List<AllReserveVo> allReserveVos = new LinkedList<>();
        reserveGymVos.stream().collect(Collectors.groupingBy((item) -> item.getGymName())).forEach((key, v) -> {
            AllReserveVo vo = new AllReserveVo();
            vo.setReserve(v.stream().collect(Collectors.toList()));
            vo.setName(key);
            allReserveVos.add(vo);
        });
        return allReserveVos;
    }

}
