package com.cpy.takeout.dao;



import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpy.takeout.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

public interface UserMapper extends BaseMapper<User> {
    @Select("select * from user where username=#{username}")
    User selectByUsername(String username);

    @Insert("insert into user(username,password,nickname,avatar) values(#{username},#{password},#{nickname},#{avatar})")
    int insert(User user);
}