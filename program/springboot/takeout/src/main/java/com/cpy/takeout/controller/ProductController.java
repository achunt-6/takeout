package com.cpy.takeout.controller;

import com.cpy.takeout.util.RedisCacheUtil;
import com.cpy.takeout.util.Result;
import com.cpy.takeout.dto.ProductQueryDTO;
import com.cpy.takeout.entity.Product;
import com.cpy.takeout.service.ProductService;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/goods")
public class ProductController {

    @Resource
    private ProductService productService;

    @Resource
    private RedisCacheUtil redisCacheUtil;

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

    // 商品详情接口（带 Redis 缓存）
    @GetMapping("/detail")
    public Result getDetail(@RequestParam Long id) {
        String redisKey = "product:detail:" + id;
        // 1. 先查 Redis 缓存
        Object cached = redisCacheUtil.get(redisKey);
        if (cached != null) {
            return Result.success(cached);
        }
        // 2. 缓存未命中，查数据库
        Product product = productService.getById(id);
        // 3. 回写 Redis，过期时间 10 分钟
        redisCacheUtil.set(redisKey, product, 10, TimeUnit.MINUTES);
        return Result.success(product);
    }

    // 后台管理：多条件组合分页查询（完整路径: POST /admin/goods/search）
    @PostMapping("/admin/search")
    public Result search(@RequestBody ProductQueryDTO dto) {
        Page<Product> page = productService.search(dto);
        return Result.success(page);
    }
}