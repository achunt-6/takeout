package com.cpy.takeout.controller;

import com.cpy.takeout.util.Result;
import com.cpy.takeout.entity.Product;
import com.cpy.takeout.service.ProductService;
import com.cpy.takeout.util.Result;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/goods")
public class ProductController {

    @Resource
    private ProductService productService;

    // 小程序商品列表接口（对接前端）
    @GetMapping("/list")
    public Result getGoodsList(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer size
    ) {
        List<Product> productList = productService.list(page, size);
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Product p : productList) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", p.getId());
            map.put("name", p.getName());
            map.put("price", p.getPrice());
            map.put("desc", p.getDescription());
            map.put("image", p.getImage());
            map.put("num", 1); // 默认数量1
            resultList.add(map);
        }
        return Result.success(resultList);
    }

    // 商品详情接口
    @GetMapping("/detail")
    public Result getDetail(@RequestParam Long id) {
        Product product = productService.getById(id);
        return Result.success(product);
    }
}