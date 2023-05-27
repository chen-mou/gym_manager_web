package com.gm.gym_manager_web;

import com.gm.gym_manager_web.conf.Context;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.io.File;

@SpringBootApplication
public class GymManagerWebApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(GymManagerWebApplication.class, args);
        Context.setApplicationContext(context);
        String path = context.getEnvironment().getProperty("server.base_path");
        File f = new File(path);
        if(!f.isDirectory()){
            System.out.println(f.mkdirs());
        }
    }

}
