package com.lomtom.search.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lomtom.search.main.entity.Verification;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 19:57
 */

public interface SendMailService extends IService<Verification> {

    /**
     * 异步请求
     *
     * @param email 邮箱地址
     * @return 发送成功
     */
    boolean sendMail(String email);

    /**
     * 验证用户信息
     *
     * @param email 邮箱
     * @param code  验证码
     * @return 是否验证成功
     */
    int verification(String email, String code);
}
