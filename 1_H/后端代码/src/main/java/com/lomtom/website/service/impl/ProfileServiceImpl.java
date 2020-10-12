package com.lomtom.website.service.impl;

import com.lomtom.website.dao.ProfileDao;
import com.lomtom.website.entity.ProfileEntity;
import com.lomtom.website.service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/7
 * @Time: 20:48
 * @Email: lomtom@qq.com
 */

@Service
public class ProfileServiceImpl implements ProfileService {
    @Autowired
    ProfileDao profileDao;
    @Override
    public List<ProfileEntity> getAll() {
        return profileDao.getAll();
    }

    @Override
    public ProfileEntity getOneById(Integer id) {
        return profileDao.getOneById(id);
    }

    @Override
    public ProfileEntity getOneByName(String name) {
        return profileDao.getOneByName(name);
    }
}
