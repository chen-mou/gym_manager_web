package com.gm.gym_manager_web.conf;

import org.springframework.context.ApplicationContext;

/**
 * @author 陈桢梁
 * @desc Context.java
 * @date 2023-05-09 01:14
 * @logs[0] 2023-05-09 01:14 陈桢梁 创建了Context.java文件
 */
public class Context  {

    public static ApplicationContext app;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        if (app == null) {
            app = applicationContext;
        }
    }


}
