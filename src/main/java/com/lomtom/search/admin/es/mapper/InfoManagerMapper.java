package com.lomtom.search.admin.es.mapper;

import com.lomtom.search.main.entity.InfoEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 12:06
 */

@Repository
public interface InfoManagerMapper extends ElasticsearchRepository<InfoEntity,Long> {
}
