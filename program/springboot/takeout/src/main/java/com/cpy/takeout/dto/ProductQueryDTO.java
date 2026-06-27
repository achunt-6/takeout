package com.cpy.takeout.dto;

import lombok.Data;

@Data
public class ProductQueryDTO {
    /** 商品名（模糊匹配） */
    private String name;
    /** 最低价 */
    private Double minPrice;
    /** 最高价 */
    private Double maxPrice;
    /** 状态 */
    private Integer status;
    /** 页码（默认第1页） */
    private Integer page = 1;
    /** 每页条数（默认5条） */
    private Integer size = 5;
}
