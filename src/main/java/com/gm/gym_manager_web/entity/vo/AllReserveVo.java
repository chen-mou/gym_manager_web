package com.gm.gym_manager_web.entity.vo;

import com.gm.gym_manager_web.entity.ReserveEntity;
import com.gm.gym_manager_web.entity.base.GoodsBase;
import lombok.Data;
import java.util.List;

/**
 * @author 陈桢梁
 * @desc AllReserveVo.java
 * @date 2023-05-17 22:42
 * @logs[0] 2023-05-17 22:42 陈桢梁 创建了AllReserveVo.java文件
 */
@Data
public class AllReserveVo {

    private String name;

    List<ReserveEntity> reserve;

}
