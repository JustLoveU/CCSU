package com.lomtom.search.admin.service.impl;

import com.lomtom.search.admin.mapper.AdminUniversityMapper;
import com.lomtom.search.admin.service.AdminUniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/19
 * @Time: 10:03
 */
@Service
public class AdminUniversityServiceImpl implements AdminUniversityService {


    @Autowired
    private AdminUniversityMapper universityMapper;

    @Override
    public long count() {
        return universityMapper.count();
    }
}
