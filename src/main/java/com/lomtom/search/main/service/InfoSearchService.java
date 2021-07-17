package com.lomtom.search.main.service;

import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/14
 * @Time: 15:34
 */

public interface InfoSearchService {

    /**
     * 按照查询条件进行数据查询
     */
    Map<String,Object> search(Map<String,String> searchMap);


}
