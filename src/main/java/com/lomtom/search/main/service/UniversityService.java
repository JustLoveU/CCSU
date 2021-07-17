package com.lomtom.search.main.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.lomtom.search.main.entity.UniversityEntity;

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


    List<UniversityEntity> ListByUniversity(Integer id);
}

