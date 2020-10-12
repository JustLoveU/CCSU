package com.lomtom.website_university.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.entity.CommentEntity;

import java.util.Map;

/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-08-28 12:18:05
 */
public interface CommentService extends IService<CommentEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

