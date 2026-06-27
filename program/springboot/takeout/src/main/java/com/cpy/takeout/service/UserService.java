package com.cpy.takeout.service;


import com.cpy.takeout.entity.User;

public interface UserService {
    User login(User user);
    void register(User user);
    void updateById(User user);
    boolean updateAvatar(Long userId, String avatarUrl);
}
