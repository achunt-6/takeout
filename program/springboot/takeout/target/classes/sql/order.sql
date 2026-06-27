-- =============================================
-- 订单表建表语句
-- 请在 MySQL 中执行此 SQL 创建 order 表
-- =============================================
CREATE TABLE IF NOT EXISTS `order` (
    `id`          BIGINT       NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    `user_id`     BIGINT       NOT NULL                COMMENT '用户ID',
    `product_id`  BIGINT       NOT NULL                COMMENT '商品ID',
    `amount`      DECIMAL(10,2) NOT NULL               COMMENT '订单金额',
    `status`      CHAR(1)      NOT NULL DEFAULT '0'    COMMENT '订单状态: 0-待支付, 1-已支付, 2-已取消',
    `create_time` DATETIME     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    PRIMARY KEY (`id`),
    INDEX `idx_user_id` (`user_id`),
    INDEX `idx_status_create_time` (`status`, `create_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单表';
