package com.cpy.takeout.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.cpy.takeout.entity.Product;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductMapper extends BaseMapper<Product> {

    // 分页查询商品（适配ServiceImpl的selectList，保留原有手动分页逻辑）
    @Select("SELECT * FROM product LIMIT #{start}, #{size}")
    List<Product> selectList(Integer start, Integer size);

    // 根据ID查询商品
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product selectById(Long id);

    // 新增商品
    @Insert("INSERT INTO product(name, price, description, image, status, create_time) " +
            "VALUES(#{name}, #{price}, #{description}, #{image}, #{status}, #{createTime})")
    int insert(Product product);

    // 修改商品
    @Update("UPDATE product SET name=#{name}, price=#{price}, description=#{description}, " +
            "image=#{image}, status=#{status} WHERE id=#{id}")
    void update(Product product);

    // 删除商品
    @Delete("DELETE FROM product WHERE id=#{id}")
    int deleteById(Long id);
}
