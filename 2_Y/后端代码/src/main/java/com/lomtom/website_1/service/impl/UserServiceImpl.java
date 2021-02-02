package com.lomtom.website_1.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lomtom.website_1.dao.UserDao;
import com.lomtom.website_1.entity.UserEntity;
import com.lomtom.website_1.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


@Service("userService")
public class UserServiceImpl implements UserService {

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