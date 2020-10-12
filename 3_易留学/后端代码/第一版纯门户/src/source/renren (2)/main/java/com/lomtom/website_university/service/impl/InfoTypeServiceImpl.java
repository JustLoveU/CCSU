package com.lomtom.website_university.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.Query;

import com.lomtom.website_university.dao.InfoTypeDao;
import com.lomtom.website_university.entity.InfoTypeEntity;
import com.lomtom.website_university.service.InfoTypeService;


@Service("infoTypeService")
public class InfoTypeServiceImpl extends ServiceImpl<InfoTypeDao, InfoTypeEntity> implements InfoTypeService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<InfoTypeEntity> page = this.page(
                new Query<InfoTypeEntity>().getPage(params),
                new QueryWrapper<InfoTypeEntity>()
        );

        return new PageUtils(page);
    }

}