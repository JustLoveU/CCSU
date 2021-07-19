package com.lomtom.website_university.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lomtom.website_university.entity.CollectionEntity;
import com.lomtom.website_university.service.CollectionService;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.R;



/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:31
 */
@RestController
@RequestMapping("website_university/collection")
@Api(tags = "收藏接口")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = collectionService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{id}")
//    //@RequiresPermissions("website_university:collection:info")
//    public R info(@PathVariable("id") Integer id){
//		CollectionEntity collection = collectionService.getById(id);
//
//        return R.ok().put("collection", collection);
//    }
//
    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存收藏")
    public R save(@RequestBody CollectionEntity collection){
		collectionService.save(collection);
        return R.ok().put("code",200);
    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    //@RequiresPermissions("website_university:collection:update")
//    public R update(@RequestBody CollectionEntity collection){
//		collectionService.updateById(collection);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Integer[] ids){
//        System.out.println(ids);
//		collectionService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

    /**
     * 删除
     */
    @GetMapping("/deleteOne/{id}")
    @ApiOperation("取消收藏")
    public R deleteOne(@PathVariable("id") Integer id){
        collectionService.removeById(id);

        return R.ok().put("code",200);
    }
    /**
     * 保存
     */
    @PostMapping("/one")
    @ApiOperation("获取是否收藏")
    public R one(@RequestBody CollectionEntity collection){
        CollectionEntity collectionEntity = collectionService.getOne(
                new QueryWrapper<CollectionEntity>()
                        .eq("user_id", collection.getUserId())
                        .eq("university_id", collection.getUniversityId()));
        return R.ok().put("code",collectionEntity==null?404:200).put("collection",collectionEntity);
    }

}
