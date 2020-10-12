package com.lomtom.website_1.service;

import com.github.pagehelper.PageInfo;
import com.lomtom.website_1.entity.UserEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-06-29 16:24:02
 */
public interface UserService {

    PageInfo queryPage(Map<String, Object> params);

    UserEntity getById(Integer id);

    void save(UserEntity userEntity);

    void updateById(UserEntity userEntity);

    void removeByIds(List<Integer> asList);

    UserEntity login(UserEntity user);
}

