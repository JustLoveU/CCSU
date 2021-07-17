package com.lomtom.search.admin.es.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lomtom.search.admin.es.entity.EsOperateLog;
import com.lomtom.search.admin.es.service.ESManagerUniversityService;
import com.lomtom.search.admin.es.service.ESUniversityService;
import com.lomtom.search.admin.es.service.EsOperateLogService;
import com.lomtom.search.admin.service.AdminUniversityService;
import com.lomtom.search.common.pojo.Result;
import com.lomtom.search.main.service.UniversitySearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import xyz.erupt.core.constant.EruptRestPath;

import java.util.List;
import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/19
 * @Time: 9:49
 */

@RestController
@RequestMapping(EruptRestPath.ERUPT_API + "/es/university")
public class EsUniversityController {
    @Autowired
    private ESManagerUniversityService managerUniversityService;

    @Autowired
    private ESUniversityService esUniversityService;

    @Autowired
    private AdminUniversityService universityService;

    @Autowired
    private EsOperateLogService esOperateLogService;

    @Autowired
    private UniversitySearchService universitySearchService;
    /**
     * 创建院校索引与映射
     */
    @GetMapping("/create")
    public Result createInfoMappingAndIndex(){
        managerUniversityService.createUniversityMappingAndIndex();
        return  Result.ok();
    }

    /**
     * 导入院校的信息
     */
    @GetMapping("/importAll")
    public Result importInfoAll(){
        managerUniversityService.importUniversityAll();
        return Result.ok();
    }

    /**
     * 首次加载
     * @return 数据
     */
    @GetMapping("/index")
    public Result index(){
        long countEs = esUniversityService.count();
        long countMysql = universityService.count();
        List<EsOperateLog> list = esOperateLogService.list(new QueryWrapper<EsOperateLog>().eq("type",2).orderByDesc("create_time").last("limit 6"));
        return Result.ok().put("countEs",countEs).put("countMysql",countMysql).put("list",list);
    }


    /**
     * 分页
     * @return 数据
     */
    @GetMapping("/list")
    public Result list(@RequestParam Map<String, String> params){
        Map<String,Object> page = universitySearchService.search(params);
        return Result.ok().put("page",page);
    }

}
