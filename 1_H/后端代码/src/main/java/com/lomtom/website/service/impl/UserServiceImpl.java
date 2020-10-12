package com.lomtom.website.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

import com.lomtom.website.dao.UserDao;
import com.lomtom.website.entity.UserEntity;
import com.lomtom.website.service.UserService;


@Service("userService")
public class UserServiceImpl  implements UserService {

    @Autowired
    UserDao userDao;

    @Override
    public PageInfo queryPage(Map<String, Object> params) {
        PageHelper.startPage(Integer.parseInt(String.valueOf(params.get("page"))),Integer.parseInt(String.valueOf(params.get("limit"))));
        return new PageInfo<>(userDao.queryPage());
    }

    @Override
    public UserEntity getById(Integer id) {
        return userDao.getById(id);
    }

    @Override
    public void save(UserEntity userEntity) {
        userDao.save(userEntity);
    }

    @Override
    public void updateById(UserEntity userEntity) {
        userDao.updateById(userEntity);
    }

    @Override
    public void removeByIds(List<Integer> asList) {
        for (Integer id: asList) {
            userDao.removeByIds(id);
        }
    }

    @Override
    public UserEntity login(UserEntity user) {
        return userDao.login(user);
    }
}