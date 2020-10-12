package com.lomtom.website_university.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.entity.InfoEntity;

import java.util.Map;

/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
public interface InfoService extends IService<InfoEntity> {

    PageUtils queryPage(Map<String, Object> params);
    PageUtils queryPage(Map<String, Object> params,Integer id);
    PageUtils queryPage(Map<String, Object> params,String search);
}

