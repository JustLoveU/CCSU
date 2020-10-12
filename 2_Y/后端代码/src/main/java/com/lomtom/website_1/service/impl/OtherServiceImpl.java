package com.lomtom.website_1.service.impl;

import com.lomtom.website_1.dao.OtherMapper;
import com.lomtom.website_1.entity.Other;
import com.lomtom.website_1.service.OtherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LOMTOM
 * @Date: 2020/7/3
 * @Time: 18:14
 * @Email: lomtom@qq.com
 */
@Service
public class OtherServiceImpl implements OtherService {

    @Autowired
    OtherMapper otherMapper;

    @Override
    public Other getOther(Integer id) {
        return otherMapper.getOther(id);
    }

    @Override
    public void upateOther(Other other) {
        otherMapper.upateOther(other);
    }
}
