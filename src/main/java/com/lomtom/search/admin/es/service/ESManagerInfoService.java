package com.lomtom.search.admin.es.service;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 12:11
 */

public interface ESManagerInfoService {

    /**
     * 创建资讯索引与映射
     */
    void createInfoMappingAndIndex();

    /**
     * 导入资讯的信息
     */
    void importInfoAll();

    /**
     * 按照编号导入资讯
     * @param id 编号
     */
    void importInfoById(Long id);

    /**
     * 按照编号删除资讯
     * @param id 编号
     */
    void deleteInfoById(Long id);



}
