package com.cpy.takeout.dao;

import com.cpy.takeout.entity.Product;
import org.apache.ibatis.annotations.*;
import java.util.List;

@Mapper
public interface ProductMapper {

    // 分页查询商品（适配ServiceImpl的selectList）
    @Select("SELECT * FROM product LIMIT #{start}, #{size}")
    List<Product> selectList(Integer start, Integer size);

    // 根据ID查询商品（适配selectById）
    @Select("SELECT * FROM product WHERE id = #{id}")
    Product selectById(Long id);

    // 新增商品（适配insert）
    @Insert("INSERT INTO product(name, price, description, image, status, create_time) " +
            "VALUES(#{name}, #{price}, #{description}, #{image}, #{status}, #{createTime})")
    void insert(Product product);

    // 修改商品（适配update）
    @Update("UPDATE product SET name=#{name}, price=#{price}, description=#{description}, " +
            "image=#{image}, status=#{status} WHERE id=#{id}")
    void update(Product product);

    // 删除商品（适配deleteById）
    @Delete("DELETE FROM product WHERE id=#{id}")
    void deleteById(Long id);
}