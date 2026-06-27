package com.cpy.takeout.service;

import com.cpy.takeout.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> list(Integer page, Integer size);
    Product getById(Long id);
    void add(Product product);
    void update(Product product);
    void delete(Long id);
}