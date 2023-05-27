package com.gm.gym_manager_web.service;

import com.gm.gym_manager_web.dao.jpa.GymWorkerRepository;
import com.gm.gym_manager_web.dao.jpa.UserRepository;
import com.gm.gym_manager_web.entity.GymWorkerEntity;
import com.gm.gym_manager_web.entity.UserEntity;
import com.gm.gym_manager_web.entity.vo.RegisterVo;
import jakarta.annotation.Resource;
import org.hibernate.Session;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tool.SessionUtil;
import tool.enums.Role;

/**
 * @author 陈桢梁
 * @desc UserService.java
 * @date 2023-05-09 14:45
 * @logs[0] 2023-05-09 14:45 陈桢梁 创建了UserService.java文件
 */
@Service
public class UserService {

    @Value("${server.url_prefix}")
    String urlPrefix;

    @Resource
    UserRepository userRepository;

    @Resource
    GymWorkerRepository gymWorkerRepository;

    @Transactional
    public UserEntity register(RegisterVo vo){
        UserEntity user = userRepository.findByName(vo.getName());
        if(user != null){
            throw new RuntimeException("用户名已经存在了");
        }
        user = new UserEntity();
        user.setName(vo.getName());
        user.setPassword(vo.getPassword());
        user.setRole(Role.Custom);
        user.setAvatar(urlPrefix + "/default.jpg");
        user.setNickname("新用户");
        SessionUtil.getSession().setAttribute("user", user);
        userRepository.save(user);
        return user;
    }

    public UserEntity login(RegisterVo vo){
        UserEntity user = userRepository.findByName(vo.getName());
        if(user == null){
            throw new RuntimeException("用户名不存在");
        }
        if(!user.getPassword().equals(vo.getPassword())){
            throw new RuntimeException("密码有误");
        }
        Object obj = SessionUtil.getSession().getAttribute("user");
        if(obj != null){
            return user;
        }
        SessionUtil.getSession().setAttribute("user", user);
        return user;
    }

    @Transactional
    public UserEntity workLogin(RegisterVo vo){
        UserEntity user = login(vo);
        GymWorkerEntity worker = gymWorkerRepository.findGymWorkerByUserId(user.getId());
        if(worker == null){
            throw new RuntimeException("没有权限");
        }
        Object obj = SessionUtil.getSession().getAttribute("user");
        if(obj != null){
            return user;
        }
        SessionUtil.getSession().setAttribute("user", user);
        return user;
    }

}
