package com.lomtom.search.main.service;

import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/14
 * @Time: 15:32
 */

public interface UniversitySearchService {

    /**
     * 按照查询条件进行数据查询
     */
    Map search(Map<String,String> searchMap);
}
