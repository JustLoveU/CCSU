package com.lomtom.search.admin.es.service.impl;

import com.lomtom.search.common.annotation.EsOperate;
import com.lomtom.search.admin.es.mapper.InfoManagerMapper;
import com.lomtom.search.main.entity.InfoEntity;
import com.lomtom.search.admin.es.service.ESManagerInfoService;
import com.lomtom.search.admin.mapper.AdminInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.document.Document;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 12:11
 */
@Service
public class ESManagerInfoServiceImpl implements ESManagerInfoService {

    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Autowired
    private AdminInfoMapper infoMapper;

    @Autowired
    private InfoManagerMapper infoManagerMapper;


    @EsOperate(value = "ES | 新建索引",type = 1)
    @Override
    public void createInfoMappingAndIndex() {
        boolean exists = elasticsearchRestTemplate.indexOps(InfoEntity.class).exists();
        if (exists){
            elasticsearchRestTemplate.indexOps(InfoEntity.class).delete();
        }
        boolean create = elasticsearchRestTemplate.indexOps(InfoEntity.class).create();
        if (create){
            Document mapping = elasticsearchRestTemplate.indexOps(InfoEntity.class).createMapping();
            elasticsearchRestTemplate.indexOps(InfoEntity.class).putMapping(mapping);
        }
    }

    @EsOperate(value = "ES | 导入",type = 1)
    @Override
    public void importInfoAll() {
        List<InfoEntity> list = infoMapper.selectAll();
        if (list == null || list.size() <= 0){
            // TODO: 2021/4/11 异常处理
            throw new RuntimeException("当前没有数据被查询到,无法导入索引库");
        }
        infoManagerMapper.saveAll(list);
    }

    @Override
    public void importInfoById(Long id) {
        InfoEntity info = infoMapper.selectById(id);
        if (info == null){
            // TODO: 2021/4/11 异常处理
            throw new RuntimeException("当前没有数据被查询到,无法导入索引库");
        }
        infoManagerMapper.save(info);
    }

    @Override
    public void deleteInfoById(Long id) {
        infoManagerMapper.deleteById(id);
    }

}
