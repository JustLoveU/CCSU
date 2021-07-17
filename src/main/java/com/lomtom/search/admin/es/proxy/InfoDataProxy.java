package com.lomtom.search.admin.es.proxy;

import com.lomtom.search.admin.entity.InfoEntity;
import com.lomtom.search.common.annotation.EsOperate;
import com.lomtom.search.admin.es.service.ESManagerInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import xyz.erupt.annotation.fun.DataProxy;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 14:50
 */
@Service
public class InfoDataProxy implements DataProxy<InfoEntity> {

    @Autowired
    private ESManagerInfoService esManagerInfoService;

//    @EsOperate(value = "ES | 添加",type = 1)
//    @Override
//    public void afterAdd(InfoEntity infoEntity) {
//        esManagerInfoService.importInfoById(infoEntity.getId());
//    }
//
//    @EsOperate(value = "ES | 更新",type = 1)
//    @Override
//    public void afterUpdate(InfoEntity infoEntity) {
//        esManagerInfoService.deleteInfoById(infoEntity.getId());
//        esManagerInfoService.importInfoById(infoEntity.getId());
//    }
//
//
//    @EsOperate(value = "ES | 删除",type = 1)
//    @Override
//    public void afterDelete(InfoEntity infoEntity) {
//        esManagerInfoService.deleteInfoById(infoEntity.getId());
//    }
}
