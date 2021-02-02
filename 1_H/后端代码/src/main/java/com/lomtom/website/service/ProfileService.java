package com.lomtom.website.service;

import com.lomtom.website.entity.ProfileEntity;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/7
 * @Time: 16:03
 * @Email: lomtom@qq.com
 */

public interface ProfileService {
    List<ProfileEntity> getAll();

    ProfileEntity getOneById(Integer id);

    ProfileEntity getOneByName(String name);

    List<ProfileEntity> getHome();
}
