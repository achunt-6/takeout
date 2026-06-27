package com.cpy.takeout.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.cpy.takeout.entity.Order;

public interface OrderService extends IService<Order> {
    /**
     * 取消超时未支付的订单（超过30分钟未支付）
     */
    void cancelTimeoutOrders();
}
