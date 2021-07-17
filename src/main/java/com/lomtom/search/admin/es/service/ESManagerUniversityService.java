package com.lomtom.search.admin.es.service;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/19
 * @Time: 10:00
 */

public interface ESManagerUniversityService {






    /**
     * 创建院校索引与映射
     */
    void createUniversityMappingAndIndex();

    /**
     * 导入院校的信息
     */
    void importUniversityAll();

    /**
     * 按照编号导入院校
     * @param id 编号
     */
    void importUniversityById(Long id);

    /**
     * 按照编号删除院校
     * @param id 编号
     */
    void deleteUniversityById(Long id);
}

