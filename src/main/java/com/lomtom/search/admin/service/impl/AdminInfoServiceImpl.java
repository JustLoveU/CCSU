package com.lomtom.search.admin.service.impl;

import com.lomtom.search.admin.mapper.AdminInfoMapper;
import com.lomtom.search.admin.service.AdminInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 19:26
 */

@Service
public class AdminInfoServiceImpl implements AdminInfoService {

    @Autowired
    private AdminInfoMapper infoMapper;

    @Override
    public long count() {
        return infoMapper.count();
    }
}
