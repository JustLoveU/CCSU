package com.lomtom.search.main.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.search.main.entity.UniversityEntity;
import com.lomtom.search.main.mapper.UniversityMapper;
import com.lomtom.search.main.service.UniversityService;
import org.springframework.stereotype.Service;

import java.util.List;


@DS("slave")
@Service
public class UniversityServiceImpl extends ServiceImpl<UniversityMapper, UniversityEntity> implements UniversityService {


    @Override
    public List<UniversityEntity> ListByUniversity(Integer id) {
        return baseMapper.ListByUniversity(id);
    }
}