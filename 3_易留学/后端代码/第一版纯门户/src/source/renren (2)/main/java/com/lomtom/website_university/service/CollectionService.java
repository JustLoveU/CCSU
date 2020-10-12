package com.lomtom.website_university.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.entity.CollectionEntity;

import java.util.Map;

/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-08-28 12:18:05
 */
public interface CollectionService extends IService<CollectionEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

