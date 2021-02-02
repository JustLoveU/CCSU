package com.lomtom.website_university.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.Query;

import com.lomtom.website_university.dao.ServiceDao;
import com.lomtom.website_university.entity.ServiceEntity;
import com.lomtom.website_university.service.ServiceService;


@Service("serviceService")
public class ServiceServiceImpl extends ServiceImpl<ServiceDao, ServiceEntity> implements ServiceService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ServiceEntity> page = this.page(
                new Query<ServiceEntity>().getPage(params),
                new QueryWrapper<ServiceEntity>()
        );

        return new PageUtils(page);
    }

}