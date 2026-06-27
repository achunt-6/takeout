package com.cpy.takeout.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.cpy.takeout.dao.OrderMapper;
import com.cpy.takeout.entity.Order;
import com.cpy.takeout.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    private static final Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Override
    public void cancelTimeoutOrders() {
        // 计算30分钟前的时间点
        LocalDateTime deadline = LocalDateTime.now().minusMinutes(30);

        // 查询超时未支付的订单
        LambdaUpdateWrapper<Order> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(Order::getStatus, "0")                    // 待支付
               .lt(Order::getCreateTime, deadline);           // 创建时间早于30分钟前

        // 先查出待取消的订单ID列表（用于日志）
        List<Order> timeoutOrders = this.list(
                new com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper<Order>()
                        .eq(Order::getStatus, "0")
                        .lt(Order::getCreateTime, deadline)
        );

        if (timeoutOrders.isEmpty()) {
            return;
        }

        // 将订单状态更新为 '2'（已取消）
        wrapper.set(Order::getStatus, "2");
        this.update(wrapper);

        // 打印被取消的订单 ID 列表
        List<Long> canceledIds = timeoutOrders.stream()
                .map(Order::getId)
                .collect(Collectors.toList());
        log.info("超时订单自动取消完成，被取消的订单ID: {}", canceledIds);
    }
}
