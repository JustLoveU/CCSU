package com.lomtom.search.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.lomtom.search.common.pojo.Result;
import com.lomtom.search.common.util.PageUtils;
import com.lomtom.search.main.entity.InfoEntity;
import com.lomtom.search.main.service.InfoSearchService;
import com.lomtom.search.main.service.InfoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/13
 * @Time: 18:02
 */

@Api(tags = "资讯接口")
@RestController
@RequestMapping("/info")
public class InfoController {

    @Autowired
    private InfoService infoService;

    @Autowired
    private InfoSearchService infoSearchService;

    @ApiOperation("获取首页热门板块")
    @GetMapping("/hot/{number}")
    public Result hotInfo(@PathVariable Integer number){
        List<InfoEntity> list = infoService.list(new QueryWrapper<InfoEntity>()
                .select("id","title","time","view","source","pic")
                .orderBy(true,false,"view").last(" limit " + number));
        return Result.ok().put("list",list);
    }

    /**
     * 根据分类获取资讯分页
     */
    @GetMapping("/listByType")
    @ApiOperation("根据分类获取资讯分页")
    public Result list2(@RequestParam Map<String, Object> params, @RequestParam("id") Integer id){
        PageUtils page = infoService.queryPage(params,id);
        return Result.ok().put("page", page);
    }

    /**
     * 获取某篇资讯
     */
    @ApiOperation("获取某篇资讯")
    @GetMapping("/info/{id}")
    public Result oneInfo(@PathVariable("id") Integer id){
        InfoEntity info = infoService.getById(id);
        infoService.update(new UpdateWrapper<InfoEntity>().setSql(true,"view = view + 1"));
        return Result.ok().put("info", info);
    }


    /**
     * 搜索
     */
    @GetMapping("/search")
    @ApiOperation("搜索获取资讯")
    public Result search(@RequestParam Map<String, String> params){
        Map<String,Object> page = infoSearchService.search(params);
        return Result.ok().put("page",page);
    }


    @GetMapping("/ListByUniversity/{id}")
    @ApiOperation("通过大学获取资讯")
    public Result ListByUniversity(@PathVariable("id") Integer id){
        List<InfoEntity> list = infoService.list(new QueryWrapper<InfoEntity>().eq("university_id",id)
                .orderBy(true,false,"view").last(" limit 6"));
        return Result.ok().put("list",list);
    }


}
