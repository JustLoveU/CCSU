package com.lomtom.search.admin.es.service.impl;

import com.lomtom.search.admin.es.service.ESUniversityService;
import com.lomtom.search.main.entity.UniversityEntity;
import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchRestTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/19
 * @Time: 10:06
 */
@Service
public class ESUniversityServiceImpl implements ESUniversityService {
    @Autowired
    private ElasticsearchRestTemplate elasticsearchRestTemplate;

    @Override
    public long count() {
        NativeSearchQueryBuilder nativeSearchQueryBuilder = new NativeSearchQueryBuilder();
        BoolQueryBuilder booleanQuery = QueryBuilders.boolQuery();
        nativeSearchQueryBuilder.withQuery(booleanQuery);
        return elasticsearchRestTemplate.count(nativeSearchQueryBuilder.build(), UniversityEntity.class);
    }
}
