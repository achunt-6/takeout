package com.cpy.takeout.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import javax.annotation.PostConstruct;

@Configuration
public class FileConfig {
    @Value("${file.upload-path}")
    private String uploadPath;

    public static String UPLOAD_PATH;

    @PostConstruct
    public void init(){
        UPLOAD_PATH = this.uploadPath;
    }
}