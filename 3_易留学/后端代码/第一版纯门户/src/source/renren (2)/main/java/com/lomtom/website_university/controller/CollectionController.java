package com.lomtom.website_university.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lomtom.website_university.entity.CollectionEntity;
import com.lomtom.website_university.service.CollectionService;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.R;



/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-08-28 12:18:05
 */
@RestController
@RequestMapping("website_university/collection")
public class CollectionController {
    @Autowired
    private CollectionService collectionService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("website_university:collection:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = collectionService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("website_university:collection:info")
    public R info(@PathVariable("id") Integer id){
		CollectionEntity collection = collectionService.getById(id);

        return R.ok().put("collection", collection);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("website_university:collection:save")
    public R save(@RequestBody CollectionEntity collection){
		collectionService.save(collection);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("website_university:collection:update")
    public R update(@RequestBody CollectionEntity collection){
		collectionService.updateById(collection);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("website_university:collection:delete")
    public R delete(@RequestBody Integer[] ids){
		collectionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
