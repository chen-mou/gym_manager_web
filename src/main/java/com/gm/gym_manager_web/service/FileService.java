package com.gm.gym_manager_web.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import tool.MD5;

import java.io.File;
import java.io.IOException;

/**
 * @author 陈桢梁
 * @desc FileService.java
 * @date 2023-05-27 14:29
 * @logs[0] 2023-05-27 14:29 陈桢梁 创建了FileService.java文件
 */
@Service
public class FileService {

    @Value("${server.url_prefix}")
    String URL_PREFIX;

    @Value("${server.base_path}")
    String BASE_PATH = "";

    public String save(MultipartFile file){
        String name = file.getOriginalFilename();
        String[] nas = name.split("\\.");
        File f = new File(BASE_PATH.replace("\\", "/") +"/" + MD5.salt(nas[0]) + "." + nas[nas.length - 1]);
        try {
            file.transferTo(f);
        }catch (IOException e){
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
        return URL_PREFIX + "/" + f.getName();
    }

}
