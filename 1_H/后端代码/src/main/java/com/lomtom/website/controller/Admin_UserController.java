package com.lomtom.website.controller;

import com.alibaba.fastjson.JSONObject;
import com.lomtom.website.entity.FriendEntity;
import com.lomtom.website.entity.UserEntity;
import com.lomtom.website.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

//  { username: 'admin', password: 'admin', uuid: 'admin-uuid', name: 'Admin' },
/**
 * @Author: LOMTOM
 * @Date: 2020/6/29
 * @Time: 16:01
 * @Email: lomtom@qq.com
 */

@RestController
@RequestMapping({"/admin/user","user"})
public class Admin_UserController {
    @PostMapping("login")
    public JSONObject login(@RequestBody UserEntity user){
        JSONObject jsonObject = new JSONObject();
        UserEntity userEntity = userService.login(user);
        jsonObject.put("code", userEntity!=null?0:404);
        jsonObject.put("user", userEntity);
        return jsonObject;
    }


    @Autowired
    private UserService userService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public JSONObject list(@RequestParam Map<String, Object> params){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", 0);
        jsonObject.put("page", userService.queryPage(params));
        return jsonObject;
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public JSONObject info(@PathVariable("id") Integer id){

        JSONObject jsonObject = new JSONObject();
        UserEntity user = userService.getById(id);
        jsonObject.put("code",0);
        jsonObject.put("user",user);
        return jsonObject;
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public JSONObject save(@RequestBody UserEntity user){
        JSONObject jsonObject = new JSONObject();
        userService.save(user);
        jsonObject.put("code",0);
        return jsonObject;
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public JSONObject update(@RequestBody UserEntity user){
        userService.updateById(user);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        return jsonObject;
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public JSONObject delete(@RequestBody Integer[] ids){
        userService.removeByIds(Arrays.asList(ids));
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        return jsonObject;
    }
}
