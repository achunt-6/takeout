package com.cpy.takeout.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component
public class FileUploadUtil {

    @Value("${file.upload-path}")
    private String uploadPath;

    public String upload(MultipartFile file) {
        try {
            File dir = new File(uploadPath);
            if (!dir.exists()) dir.mkdirs();

            String originalFilename = file.getOriginalFilename();
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            String fileName = UUID.randomUUID() + suffix;

            File dest = new File(dir, fileName);
            file.transferTo(dest);

            return "/file/" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}