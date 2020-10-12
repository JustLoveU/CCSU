package com.lomtom.website.service;

import com.lomtom.website.entity.NavEntity;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2020/6/28
 * @Time: 10:47
 * @Email: lomtom@qq.com
 */

public interface NavService {
    List<NavEntity> getAll();

    NavEntity getByid(Integer id);
}
