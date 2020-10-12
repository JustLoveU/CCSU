package com.lomtom.website_1.service.impl;

import com.lomtom.website_1.dao.ProfileDao;
import com.lomtom.website_1.dao.ProfileMapper;
import com.lomtom.website_1.entity.ProfileEntity;
import com.lomtom.website_1.service.ProfileService;
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





    @Autowired
    ProfileMapper profileMapper;





    //管理员
    @Override
    public List<ProfileEntity> getAll_admin() {
        return profileMapper.getAll_admin();
    }

    @Override
    public ProfileEntity getArticleById(Integer id) {
        return profileMapper.getArticleById(id);
    }

    @Override
    public Boolean add(ProfileEntity articleEntity) {
        return profileMapper.add(articleEntity);
    }

    @Override
    public Boolean update(ProfileEntity articleEntity) {
        return profileMapper.update(articleEntity);
    }

    @Override
    public Boolean delete(Integer id) {
        return profileMapper.delete(id);
    }

    @Override
    public void updateStatus(ProfileEntity profileEntity) {
        profileMapper.updateStatus(profileEntity);
    }
}
