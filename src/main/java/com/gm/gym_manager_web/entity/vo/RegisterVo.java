package com.gm.gym_manager_web.entity.vo;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

/**
 * @author 陈桢梁
 * @desc RegisterVo.java
 * @date 2023-05-09 14:08
 * @logs[0] 2023-05-09 14:08 陈桢梁 创建了RegisterVo.java文件
 */
@Data
public class RegisterVo {

    @NotNull(message = "用户名不能为空")
    private String name;


    @NotNull(message = "密码不能为空")
    private String password;

}
