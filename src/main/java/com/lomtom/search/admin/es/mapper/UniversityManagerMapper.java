package com.lomtom.search.admin.es.mapper;

import com.lomtom.search.main.entity.UniversityEntity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/19
 * @Time: 9:53
 */
@Repository
public interface UniversityManagerMapper  extends ElasticsearchRepository<UniversityEntity,Long> {
}
