package com.lomtom.search;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lomtom.search.main.entity.UserEntity;
import com.lomtom.search.main.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author: LOMTOM
 * @Date: 2021/5/28
 * @Time: 3:59
 */

@SpringBootTest
class test {

    @Autowired
    UserService userService;

    @Test
    void contextLoads() {
        UserEntity user = userService.getById(10);
        System.out.println(user.toString());
        user.setUsername("123");
        userService.save(user);
        userService.update(user,new UpdateWrapper<UserEntity>().eq("id",user.getId()));
        userService.removeById(9);
    }
}
