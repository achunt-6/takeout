package com.cpy.takeout.task;

import com.cpy.takeout.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class OrderTimeoutTask {

    private static final Logger log = LoggerFactory.getLogger(OrderTimeoutTask.class);

    @Resource
    private OrderService orderService;

    /**
     * 每 60 秒执行一次，扫描并取消超时未支付的订单
     */
    @Scheduled(fixedDelay = 60000)
    public void cancelTimeoutOrders() {
        log.info("开始执行超时订单取消任务...");
        try {
            orderService.cancelTimeoutOrders();
        } catch (Exception e) {
            log.error("超时订单取消任务执行异常: ", e);
        }
    }
}
