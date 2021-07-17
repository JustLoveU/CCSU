package com.lomtom.search.admin.es.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.search.admin.es.entity.EsOperateLog;
import com.lomtom.search.admin.es.mapper.EsOperateLogMapper;
import com.lomtom.search.admin.es.service.EsOperateLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 22:51
 */

@Service
public class EsOperateLogServiceImpl extends ServiceImpl<EsOperateLogMapper,EsOperateLog> implements EsOperateLogService {

    @Autowired
    private EsOperateLogMapper esOperateLogMapper;

}
