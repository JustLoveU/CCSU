package com.lomtom.website_university.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.entity.CommentAndUserEntity;
import com.lomtom.website_university.entity.UniversityEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
public interface UniversityService extends IService<UniversityEntity> {

    PageUtils queryPage(Map<String, Object> params);

    IPage<UniversityEntity> queryPage(Map<String, Object> params, Integer id);

}

