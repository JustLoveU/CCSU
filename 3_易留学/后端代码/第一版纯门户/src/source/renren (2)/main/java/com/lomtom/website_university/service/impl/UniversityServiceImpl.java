package com.lomtom.website_university.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.Query;

import com.lomtom.website_university.dao.UniversityDao;
import com.lomtom.website_university.entity.UniversityEntity;
import com.lomtom.website_university.service.UniversityService;


@Service("universityService")
public class UniversityServiceImpl extends ServiceImpl<UniversityDao, UniversityEntity> implements UniversityService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UniversityEntity> page = this.page(
                new Query<UniversityEntity>().getPage(params),
                new QueryWrapper<UniversityEntity>()
        );

        return new PageUtils(page);
    }

}