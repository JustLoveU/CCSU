package com.lomtom.search.main.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.search.main.entity.InfoTypeEntity;
import com.lomtom.search.main.mapper.InfoTypeMapper;
import com.lomtom.search.main.service.InfoTypeService;
import org.springframework.stereotype.Service;


@DS("slave")
@Service("infoTypeService")
public class InfoTypeServiceImpl extends ServiceImpl<InfoTypeMapper, InfoTypeEntity> implements InfoTypeService {
}