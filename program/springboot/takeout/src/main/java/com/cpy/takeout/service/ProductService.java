package com.cpy.takeout.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpy.takeout.dto.ProductQueryDTO;
import com.cpy.takeout.entity.Product;
import java.util.List;

public interface ProductService {
    List<Product> list(Integer page, Integer size);
    Product getById(Long id);
    void add(Product product);
    void update(Product product);
    void delete(Long id);

    /**
     * 后台多条件组合分页查询
     * @param dto 查询条件
     * @return 分页结果
     */
    Page<Product> search(ProductQueryDTO dto);
}