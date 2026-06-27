package com.cpy.takeout.controller;

import com.cpy.takeout.entity.User;
import com.cpy.takeout.service.UserService;
import com.cpy.takeout.util.FileUploadUtil;
import com.cpy.takeout.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private UserService userService;

    // 登录接口
    @PostMapping("/login")
    public Result login(@RequestBody User user, HttpSession session){
        try {
            User loginUser = userService.login(user);
            session.setAttribute("loginUser", loginUser);
            return Result.success(loginUser);
        } catch (RuntimeException e){
            return Result.error(e.getMessage());
        }
    }

    // 注册接口（调用无参success）
    @PostMapping("/register")
    public Result register(@RequestBody User user){
        try {
            userService.register(user);
            return Result.success();
        } catch (RuntimeException e){
            return Result.error(e.getMessage());
        }
    }

    @Autowired
    private FileUploadUtil fileUploadUtil;


    @PostMapping("/uploadAvatar")
    public Result uploadAvatar(
            @RequestParam Long userId,
            @RequestParam("avatar") MultipartFile file
    ) {
        String avatarUrl = fileUploadUtil.upload(file);
        if (avatarUrl == null) {
            return Result.error("上传失败");
        }

        User user = new User();
        user.setId(userId);
        user.setAvatar(avatarUrl);
        userService.updateById(user);

        return Result.success(avatarUrl);
    }


    @PostMapping("/updateInfo")
    public Result updateInfo(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }
}