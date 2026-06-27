package com.cpy.takeout.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cpy.takeout.dto.ProductQueryDTO;
import com.cpy.takeout.entity.Product;
import com.cpy.takeout.dao.ProductMapper;
import com.cpy.takeout.service.ProductService;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {
    @Resource
    private ProductMapper productMapper;

    @Override
    public List<Product> list(Integer page, Integer size) {
        int start = (page-1)*size;
        return productMapper.selectList(start, size);
    }

    @Override
    public Product getById(Long id) {
        return productMapper.selectById(id);
    }

    @Override
    public void add(Product product) {
        productMapper.insert(product);
    }

    @Override
    public void update(Product product) {
        productMapper.update(product);
    }

    @Override
    public void delete(Long id) {
        productMapper.deleteById(id);
    }

    @Override
    public Page<Product> search(ProductQueryDTO dto) {
        // 构建分页对象
        Page<Product> page = new Page<>(dto.getPage(), dto.getSize());

        // 使用 LambdaQueryWrapper 动态拼接条件
        LambdaQueryWrapper<Product> wrapper = new LambdaQueryWrapper<>();
        // 商品名模糊匹配
        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            wrapper.like(Product::getName, dto.getName().trim());
        }
        // 最低价
        if (dto.getMinPrice() != null) {
            wrapper.ge(Product::getPrice, dto.getMinPrice());
        }
        // 最高价
        if (dto.getMaxPrice() != null) {
            wrapper.le(Product::getPrice, dto.getMaxPrice());
        }
        // 状态精确匹配
        if (dto.getStatus() != null) {
            wrapper.eq(Product::getStatus, dto.getStatus());
        }

        // 执行分页查询
        return productMapper.selectPage(page, wrapper);
    }
}
