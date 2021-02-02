package com.lomtom.website_university.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.Query;

import com.lomtom.website_university.dao.ProgressDao;
import com.lomtom.website_university.entity.ProgressEntity;
import com.lomtom.website_university.service.ProgressService;


@Service("progressService")
public class ProgressServiceImpl extends ServiceImpl<ProgressDao, ProgressEntity> implements ProgressService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ProgressEntity> page = this.page(
                new Query<ProgressEntity>().getPage(params),
                new QueryWrapper<ProgressEntity>()
        );

        return new PageUtils(page);
    }

}