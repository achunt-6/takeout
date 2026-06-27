package com.cpy.takeout.service.impl;


import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.cpy.takeout.entity.User;
import com.cpy.takeout.dao.UserMapper;
import com.cpy.takeout.service.UserService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User login(User user) {
        User loginUser = userMapper.selectByUsername(user.getUsername());
        if(loginUser == null){
            throw new RuntimeException("用户名不存在");
        }
        if(!loginUser.getPassword().equals(user.getPassword())){
            throw new RuntimeException("密码错误");
        }
        return loginUser;
    }

    @Override
    public void register(User user) {
        User exist = userMapper.selectByUsername(user.getUsername());
        if(exist != null){
            throw new RuntimeException("用户名已存在");
        }
        userMapper.insert(user);
    }

    @Override
    public void updateById(User user) {
        userMapper.updateById(user);
    }

    @Override
    public boolean updateAvatar(Long userId, String avatarUrl) {
        UpdateWrapper<User> wrapper = new UpdateWrapper<>();
        wrapper.eq("id", userId);
        wrapper.set("avatar", avatarUrl);
        return userMapper.update(null, wrapper) > 0;
    }
}
