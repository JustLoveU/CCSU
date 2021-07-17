package com.lomtom.search.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.api.R;
import com.lomtom.search.common.pojo.Result;
import com.lomtom.search.common.util.PageUtils;
import com.lomtom.search.main.entity.UniversityEntity;
import com.lomtom.search.main.service.UniversitySearchService;
import com.lomtom.search.main.service.UniversityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.google.gson.Gson;
import java.util.List;
import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/13
 * @Time: 17:54
 */

@Api(tags = "院校接口")
@RestController
@RequestMapping("/university")
public class UniversityController {

    @Autowired
    private UniversityService universityService;

    @Autowired
    private UniversitySearchService universitySearchService;

    @ApiOperation("n个热门院校")
    @GetMapping("/hot/{number}")
    public Result hotUniversity(@PathVariable Integer number){
        List<UniversityEntity> list = universityService.list(new QueryWrapper<UniversityEntity>()
                .select("id","name","img","pic","url","location")
                .orderBy(true,false,"view").last(" limit " + number));
        return Result.ok().put("list", list);
    }

    /**
     * 搜索
     */
    @GetMapping("/search")
    @ApiOperation("搜索获取院校")
    public Result search(@RequestParam Map<String, String> params){
        Map<String,Object> page = universitySearchService.search(params);
        return Result.ok().put("page",page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("获取某个院校信息")
    public Result info(@PathVariable("id") Integer id){
        UniversityEntity university = universityService.getById(id);
        return Result.ok().put("university", university);
    }


    @GetMapping("/ListByUser/{id}")
    @ApiOperation("获取用户大学收藏列表")
    public Result ListByUniversity(@PathVariable("id") Integer id){
        List<UniversityEntity> list = universityService.ListByUniversity(id);
        return Result.ok().put("list", list);
    }


}
