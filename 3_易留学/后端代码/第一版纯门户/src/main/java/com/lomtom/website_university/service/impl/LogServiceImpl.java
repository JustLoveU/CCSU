package com.lomtom.website_university.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.Query;
import com.lomtom.website_university.dao.LogDao;
import com.lomtom.website_university.entity.LogEntity;
import com.lomtom.website_university.service.LogService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("logService")
public class LogServiceImpl extends ServiceImpl<LogDao, LogEntity> implements LogService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<LogEntity> page = this.page(
                new Query<LogEntity>().getPage(params),
                new QueryWrapper<LogEntity>()
        );

        return new PageUtils(page);
    }

}