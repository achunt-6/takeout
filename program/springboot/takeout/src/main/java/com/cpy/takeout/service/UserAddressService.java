package com.cpy.takeout.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpy.takeout.entity.UserAddress;
import com.cpy.takeout.dao.UserAddressMapper;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class UserAddressService extends ServiceImpl<UserAddressMapper, UserAddress> {

    // 根据用户ID查询所有地址
    public List<UserAddress> getByUserId(Long userId) {
        LambdaQueryWrapper<UserAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserAddress::getUserId, userId);
        wrapper.orderByDesc(UserAddress::getIsDefault);
        return this.list(wrapper);
    }

    // 设置默认地址
    public void setDefault(Long userId, Long id) {
        LambdaQueryWrapper<UserAddress> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(UserAddress::getUserId, userId);
        List<UserAddress> list = this.list(wrapper);

        for (UserAddress addr : list) {
            addr.setIsDefault(addr.getId().equals(id) ? 1 : 0);
            this.updateById(addr);
        }
    }
}
