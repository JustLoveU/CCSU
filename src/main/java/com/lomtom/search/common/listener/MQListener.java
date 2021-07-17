package com.lomtom.search.common.listener;

import com.lomtom.search.admin.es.service.ESManagerInfoService;
import com.lomtom.search.admin.es.service.ESManagerUniversityService;
import com.lomtom.search.common.annotation.EsOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: LOMTOM
 * @Date: 2021/5/13
 * @Time: 23:24
 */
@Component
public class MQListener {

    @Autowired
    private ESManagerInfoService esManagerInfoService;

    @Autowired
    private ESManagerUniversityService universityService;



    @EsOperate(value = "ES | 添加",type = 2)
    public void addUniversity(Long id) {
        universityService.importUniversityById(id);
    }

    @EsOperate(value = "ES | 更新",type = 2)
    public void updateUniversity(Long id) {
        universityService.deleteUniversityById(id);
        universityService.importUniversityById(id);
    }


    @EsOperate(value = "ES | 删除",type = 2)
    public void deleteUniversity(Long id) {
        universityService.deleteUniversityById(id);
    }


    @EsOperate(value = "ES | 添加",type = 1)
    public void addInfo(Long id) {
        esManagerInfoService.importInfoById(id);
    }

    @EsOperate(value = "ES | 更新",type = 1)
    public void updateInfo(Long id) {
        esManagerInfoService.deleteInfoById(id);
        esManagerInfoService.importInfoById(id);
    }


    @EsOperate(value = "ES | 删除",type = 1)
    public void deleteInfo(Long id) {
        esManagerInfoService.deleteInfoById(id);
    }
}
