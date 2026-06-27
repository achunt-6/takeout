package com.cpy.takeout.controller;

import com.cpy.takeout.entity.UserAddress;
import com.cpy.takeout.service.UserAddressService;
import com.cpy.takeout.util.Result;
import org.springframework.web.bind.annotation.*;
import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/user/address")
public class UserAddressController {

    @Resource
    private UserAddressService userAddressService;

    @GetMapping("/list")
    public Result list(@RequestParam Long userId) {
        List<UserAddress> list = userAddressService.getByUserId(userId);
        return Result.success(list);
    }

    // 新增地址
    @PostMapping("/add")
    public Result add(@RequestBody UserAddress userAddress) {
        try {
            System.out.println("【前端传过来的数据】：" + userAddress);
            System.out.println("【后端收到的数据】：" + userAddress);
            userAddressService.save(userAddress);
            return Result.success("添加成功");
        } catch (Exception e) {
            e.printStackTrace();
            return Result.error("添加失败"+e.getMessage());
        }
    }

    // 修改地址
    @PostMapping("/update")
    public Result update(@RequestBody UserAddress userAddress) {
        userAddressService.updateById(userAddress);
        return Result.success("修改成功");
    }

    // 删除地址
    @GetMapping("/delete")
    public Result delete(@RequestParam Long id) {
        userAddressService.removeById(id);
        return Result.success("删除成功");
    }

    // 设置默认地址
    @GetMapping("/default")
    public Result setDefault(@RequestParam Long userId, @RequestParam Long id) {
        userAddressService.setDefault(userId, id);
        return Result.success("设置成功");
    }
}
