package com.lomtom.website_1.controller;

import com.alibaba.fastjson.JSONObject;
import com.lomtom.website_1.dao.OtherMapper;
import com.lomtom.website_1.entity.Other;
import com.lomtom.website_1.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: LOMTOM
 * @Date: 2020/7/3
 * @Time: 18:10
 * @Email: lomtom@qq.com
 */

@RestController
@RequestMapping({"/admin/other","other"})
public class OtherController {

    @Autowired
    OtherService otherService;


    @RequestMapping("/get/{id}")
    public Other One1(@PathVariable("id") Integer id){
        return otherService.getOther(id);
    }


    @RequestMapping("/add")
    public JSONObject One2(@RequestBody Other other){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",0);
        otherService.upateOther(other);
        return jsonObject;
    }
}
