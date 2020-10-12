package com.lomtom.website_university.controller;

import com.lomtom.website_university.entity.LogEntity;
import com.lomtom.website_university.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;

/**
 * @Author: LOMTOM
 * @Date: 2020/9/21
 * @Time: 20:14
 * @Email: lomtom@qq.com
 */

@Controller
public class LogInfoController {

    @Autowired
    LogService logService;
    
    @PostMapping("/log")
    @ResponseBody
    public  String  log(@RequestBody LogEntity logEntity){
        logEntity.setDate(new Date());
        logService.save(logEntity);
        return "ok";
    }
}
