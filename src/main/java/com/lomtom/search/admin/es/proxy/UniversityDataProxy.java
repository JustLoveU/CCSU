package com.lomtom.search.admin.es.proxy;

import com.lomtom.search.admin.entity.InfoEntity;
import com.lomtom.search.admin.entity.UniversityEntity;
import com.lomtom.search.admin.es.service.ESManagerUniversityService;
import com.lomtom.search.common.annotation.EsOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.erupt.annotation.fun.DataProxy;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/19
 * @Time: 10:33
 */

@Service
public class UniversityDataProxy implements DataProxy<UniversityEntity> {

//    @Autowired
//    private ESManagerUniversityService universityService;
//
//    @EsOperate(value = "ES | 添加",type = 2)
//    @Override
//    public void afterAdd(UniversityEntity unversityEntity) {
//        universityService.importUniversityById(unversityEntity.getId());
//    }
//
//    @EsOperate(value = "ES | 更新",type = 2)
//    @Override
//    public void afterUpdate(UniversityEntity unversityEntity) {
//        universityService.deleteUniversityById(unversityEntity.getId());
//        universityService.importUniversityById(unversityEntity.getId());
//    }


//    @EsOperate(value = "ES | 删除",type = 2)
//    @Override
//    public void afterDelete(UniversityEntity unversityEntity) {
//        universityService.deleteUniversityById(unversityEntity.getId());
//    }
}
