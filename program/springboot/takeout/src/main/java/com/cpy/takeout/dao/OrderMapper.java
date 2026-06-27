package com.cpy.takeout.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpy.takeout.entity.Order;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<Order> {
}
