package com.lomtom.website_university.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.lomtom.website_university.entity.CommentAndUserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Autowired
    UniversityDao universityDao;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<UniversityEntity> page = this.page(
                new Query<UniversityEntity>().getPage(params),
                new QueryWrapper<UniversityEntity>()
        );

        return new PageUtils(page);
    }


    @Override
    public IPage<UniversityEntity> queryPage(Map<String, Object> params, Integer id) {
        long current =  Long.parseLong((String)params.get("page"));
        long size =  Long.parseLong((String)params.get("limit"));
        Page<UniversityEntity> page = new Page<>(current,size);
        return universityDao.getUniversityBYUser(page,id);
    }


}