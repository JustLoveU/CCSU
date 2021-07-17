package com.lomtom.search.main.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.search.main.entity.UserEntity;
import com.lomtom.search.main.mapper.UserMapper;
import com.lomtom.search.main.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 20:17
 */
@DS("slave")
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, UserEntity> implements UserService {
}
