package com.lomtom.search.main.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lomtom.search.common.util.PageUtils;
import com.lomtom.search.main.entity.InfoEntity;

import java.util.Map;


/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
public interface InfoService extends IService<InfoEntity> {
    PageUtils queryPage(Map<String, Object> params, Integer id);
}

