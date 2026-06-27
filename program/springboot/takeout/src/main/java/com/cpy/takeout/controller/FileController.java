package com.cpy.takeout.controller;


import com.cpy.takeout.config.FileConfig;
import com.cpy.takeout.util.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/file")
public class FileController {

    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        String fileName = UUID.randomUUID() + file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."));
        File dir = new File(FileConfig.UPLOAD_PATH);
        if(!dir.exists()) dir.mkdirs();
        file.transferTo(new File(dir, fileName));
        String url = "http://localhost:8080/file/"+fileName;
        return Result.success(url);
    }
}
