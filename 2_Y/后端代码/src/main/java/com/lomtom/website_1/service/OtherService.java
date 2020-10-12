package com.lomtom.website_1.service;

import com.lomtom.website_1.entity.Other;
/**
 * @Author: LOMTOM
 * @Date: 2020/7/3
 * @Time: 18:14
 * @Email: lomtom@qq.com
 */

public interface OtherService {

    Other getOther(Integer id);

    void upateOther(Other other);
}
