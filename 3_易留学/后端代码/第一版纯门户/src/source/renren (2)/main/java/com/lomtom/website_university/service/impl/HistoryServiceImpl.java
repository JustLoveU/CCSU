package com.lomtom.website_university.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.Query;

import com.lomtom.website_university.dao.HistoryDao;
import com.lomtom.website_university.entity.HistoryEntity;
import com.lomtom.website_university.service.HistoryService;


@Service("historyService")
public class HistoryServiceImpl extends ServiceImpl<HistoryDao, HistoryEntity> implements HistoryService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<HistoryEntity> page = this.page(
                new Query<HistoryEntity>().getPage(params),
                new QueryWrapper<HistoryEntity>()
        );

        return new PageUtils(page);
    }

}