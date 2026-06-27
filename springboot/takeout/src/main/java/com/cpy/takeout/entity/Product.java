package com.cpy.takeout.entity;

import lombok.Data;
import java.util.Date;

@Data
public class Product {
    private Long id;
    private String name;
    private Double price;
    private String description;
    private String image;
    private Integer status;
    private Date createTime;
}