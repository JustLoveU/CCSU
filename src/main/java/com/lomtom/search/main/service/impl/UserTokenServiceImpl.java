package com.lomtom.search.main.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.search.common.util.TokenGenerator;
import com.lomtom.search.main.entity.UserTokenEntity;
import com.lomtom.search.main.mapper.UserTokenMapper;
import com.lomtom.search.main.service.UserTokenService;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author: LOMTOM
 * @Date: 2020/9/29
 * @Time: 9:39
 * @Email: lomtom@qq.com
 */

@DS("slave")
@Service
public class UserTokenServiceImpl extends ServiceImpl<UserTokenMapper, UserTokenEntity> implements UserTokenService {
    //12小时后过期
    private final static int EXPIRE = 3600 * 12;

    @Override
    public String createToken(long userId) {
        //生成一个token
        String token = TokenGenerator.generateValue();

        //当前时间
        Date now = new Date();
        //过期时间
        Date expireTime = new Date(now.getTime() + EXPIRE * 1000);

        //判断是否生成过token
        UserTokenEntity tokenEntity = this.getById(userId);
        if(tokenEntity == null){
            tokenEntity = new UserTokenEntity();
            tokenEntity.setUserId(userId);
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //保存token
            this.save(tokenEntity);
        }else{
            tokenEntity.setToken(token);
            tokenEntity.setUpdateTime(now);
            tokenEntity.setExpireTime(expireTime);

            //更新token
            this.updateById(tokenEntity);
        }
        return token;
    }

    @Override
    public void logout(String token) {
        //生成一个token
        String token_new = TokenGenerator.generateValue();

        //修改token
        UserTokenEntity tokenEntity = new UserTokenEntity();
        tokenEntity.setToken(token_new);
        this.updateById(tokenEntity);
    }
}
