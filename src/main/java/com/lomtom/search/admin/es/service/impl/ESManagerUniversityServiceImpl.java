package com.lomtom.search.admin.es.service.impl;

import com.lomtom.search.admin.es.mapper.UniversityManagerMapper;
import com.lomtom.search.admin.es.service.ESManagerUniversityService;
import com.lomtom.search.admin.mapper.AdminUniversityMapper;
import com.lomtom.search.common.annotation.EsOperate;
import com.lomtom.search.main.entity.UniversityEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/19
 * @Time: 10:00
 */

@Service
public class ESManagerUniversityServiceImpl implements ESManagerUniversityService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private AdminUniversityMapper universityMapper;

    @Autowired
    private UniversityManagerMapper universityManagerMapper;

    @EsOperate(value = "ES | 新建索引",type = 2)
    @Override
    public void createUniversityMappingAndIndex() {
        boolean exists = elasticsearchRestTemplate.indexOps(UniversityEntity.class).exists();
        if (exists){
            elasticsearchRestTemplate.indexOps(UniversityEntity.class).delete();
        }
        boolean create = elasticsearchRestTemplate.indexOps(UniversityEntity.class).create();
        if (create){
            Document mapping = elasticsearchRestTemplate.indexOps(UniversityEntity.class).createMapping();
            elasticsearchRestTemplate.indexOps(UniversityEntity.class).putMapping(mapping);
        }
    }

    @EsOperate(value = "ES | 导入",type = 2)
    @Override
    public void importUniversityAll() {
        List<UniversityEntity> list = universityMapper.selectAll();
        if (list == null || list.size() <= 0){
            // TODO: 2021/4/11 异常处理
            throw new RuntimeException("当前没有数据被查询到,无法导入索引库");
        }
        universityManagerMapper.saveAll(list);
    }

    @Override
    public void importUniversityById(Long id) {
        UniversityEntity university = universityMapper.selectById1(id);
        if (university == null){
            // TODO: 2021/4/11 异常处理
            throw new RuntimeException("当前没有数据被查询到,无法导入索引库");
        }
        universityManagerMapper.save(university);
    }

    @Override
    public void deleteUniversityById(Long id) {
        universityManagerMapper.deleteById(id);
    }

}
