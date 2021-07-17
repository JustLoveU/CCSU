package com.lomtom.search.admin.es.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lomtom.search.admin.es.entity.EsOperateLog;
import com.lomtom.search.admin.es.service.EsOperateLogService;
import com.lomtom.search.common.pojo.Result;
import com.lomtom.search.admin.es.service.ESManagerInfoService;
import com.lomtom.search.admin.es.service.ESInfoService;
import com.lomtom.search.admin.service.AdminInfoService;
import com.lomtom.search.main.service.InfoSearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.erupt.core.constant.EruptRestPath;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/11
 * @Time: 12:50
 */

@RestController
@RequestMapping(EruptRestPath.ERUPT_API + "/es/info")
public class EsInfoController {


    @Autowired
    private ESManagerInfoService esManagerInfoService;

    @Autowired
    private ESInfoService esInfoService;

    @Autowired
    private AdminInfoService infoService;

    @Autowired
    private EsOperateLogService esOperateLogService;

    @Autowired
    private InfoSearchService infoSearchService;

    /**
     * 创建资讯索引与映射
     */
    @GetMapping("/create")
//    @EruptRouter(verifyType = EruptRouter.VerifyType.LOGIN, authIndex = 0)
    public Result createInfoMappingAndIndex(){
        esManagerInfoService.createInfoMappingAndIndex();
        return  Result.ok();
    }

    /**
     * 导入资讯的信息
     */
    @GetMapping("/importAll")
    public Result importInfoAll(){
        esManagerInfoService.importInfoAll();
        return Result.ok();
    }

    /**
     * 首次加载
     * @return 数据
     */
    @GetMapping("/index")
    public Result index(){
        long countEs = esInfoService.count();
        long countMysql = infoService.count();
        List<EsOperateLog> list = esOperateLogService.list(new QueryWrapper<EsOperateLog>().eq("type",1).orderByDesc("create_time").last("limit 6"));
        return Result.ok().put("countEs",countEs).put("countMysql",countMysql).put("list",list);
    }

    /**
     * 分页
     * @return 数据
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, String> params){
        Map<String,Object> page = infoSearchService.search(params);
        return Result.ok().put("page",page);
    }


}
