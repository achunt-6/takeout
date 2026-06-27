package com.cpy.takeout.service.impl;


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
}
