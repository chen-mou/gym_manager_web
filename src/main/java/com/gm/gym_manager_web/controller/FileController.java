package com.gm.gym_manager_web.controller;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.*;

/**
 * @author 陈桢梁
 * @desc FileController.java
 * @date 2023-05-27 20:56
 * @logs[0] 2023-05-27 20:56 陈桢梁 创建了FileController.java文件
 */
@RestController
@RequestMapping("/file")
public class FileController {

    @Value("${server.base_path}")
    String BASE_PATH;

    @GetMapping("/get/{name}")
    public void get(@PathVariable String name, HttpServletResponse res) throws IOException {
        File f = new File(BASE_PATH + "\\" + name);
        if(!f.exists()){
            throw new RuntimeException("文件不存在");
        }
        FileInputStream fin = new FileInputStream(f);
        try(BufferedInputStream bin = new BufferedInputStream(fin)){
            String[] names = f.getName().split("\\.");
            res.setHeader("Content-Type", "image/" + names[names.length - 1]);
            res.getOutputStream().write(bin.readAllBytes());
            res.setStatus(200);
        }
    }

}
