package com.lomtom.search.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lomtom.search.main.entity.Log;
import com.lomtom.search.main.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LOMTOM
 * @date: 2021/7/15
 * @time: 22:44
 */

@RestController
public class LogController {
    @Autowired
    LogMapper logMapper;

    @PostMapping("log")
    public void log(@RequestBody Log log){
        logMapper.insert(log);
    }


    @GetMapping("log")
    public void list(){
        logMapper.selectList(new QueryWrapper<>());
    }
}
