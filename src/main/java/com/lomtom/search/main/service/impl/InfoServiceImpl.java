package com.lomtom.search.main.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.search.common.util.PageUtils;
import com.lomtom.search.common.util.Query;
import com.lomtom.search.main.entity.InfoEntity;
import com.lomtom.search.main.mapper.InfoMapper;
import com.lomtom.search.main.service.InfoService;
import org.springframework.stereotype.Service;

import java.util.Map;


@DS("slave")
@Service
public class InfoServiceImpl extends ServiceImpl<InfoMapper, InfoEntity> implements InfoService {

    @Override
    public PageUtils queryPage(Map<String, Object> params, Integer id) {
        IPage<InfoEntity> infoEntityPage = this.page(
                new Query<InfoEntity>().getPage(params),
                new QueryWrapper<InfoEntity>().eq("type_id",id).orderByDesc("time")
        );
        return new PageUtils(infoEntityPage);
    }
}