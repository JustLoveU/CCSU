package com.lomtom.website_university.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.Query;
import com.lomtom.website_university.dao.ResultDao;
import com.lomtom.website_university.entity.ResultEntity;
import com.lomtom.website_university.service.ResultService;
import org.springframework.stereotype.Service;

import java.util.Map;


@Service("resultService")
public class ResultServiceImpl extends ServiceImpl<ResultDao, ResultEntity> implements ResultService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<ResultEntity> page = this.page(
                new Query<ResultEntity>().getPage(params),
                new QueryWrapper<ResultEntity>()
        );

        return new PageUtils(page);
    }

}