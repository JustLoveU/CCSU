package com.lomtom.search.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lomtom.search.main.entity.UserTokenEntity;

/**
 * @Author: LOMTOM
 * @Date: 2020/9/29
 * @Time: 9:36
 * @Email: lomtom@qq.com
 */

public interface UserTokenService extends IService<UserTokenEntity> {

    /**
     * 生成token
     *
     * @param userId 用户ID
     */
    String createToken(long userId);

    /**
     * 退出，修改token值
     *
     */
    void logout(String token);
}
