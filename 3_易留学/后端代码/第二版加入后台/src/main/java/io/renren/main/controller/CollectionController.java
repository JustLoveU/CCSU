package io.renren.main.controller;

import java.util.Arrays;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.main.entity.CollectionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.main.service.CollectionService;
import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.R;



/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:31
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
        return R.ok().put("code",200);
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
    public R delete(@RequestBody Integer[] ids){
        System.out.println(ids);
		collectionService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/deleteOne/{id}")
    public R deleteOne(@PathVariable("id") Integer id){
        collectionService.removeById(id);

        return R.ok().put("code",200);
    }
    /**
     * 保存
     */
    @RequestMapping("/one")
    public R one(@RequestBody CollectionEntity collection){
        CollectionEntity collectionEntity = collectionService.getOne(
                new QueryWrapper<CollectionEntity>()
                        .eq("user_id", collection.getUserId())
                        .eq("university_id", collection.getUniversityId()));
        return R.ok().put("code",collectionEntity==null?404:200).put("collection",collectionEntity);
    }

}
