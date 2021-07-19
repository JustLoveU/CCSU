package com.lomtom.website_university.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lomtom.website_university.entity.ProgressEntity;
import com.lomtom.website_university.service.ProgressService;
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
@RequestMapping("website_university/progress")
public class ProgressController {
    @Autowired
    private ProgressService progressService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("website_university:progress:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = progressService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("website_university:progress:info")
    public R info(@PathVariable("id") Integer id){
		ProgressEntity progress = progressService.getById(id);

        return R.ok().put("progress", progress);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("website_university:progress:save")
    public R save(@RequestBody ProgressEntity progress){
		progressService.save(progress);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("website_university:progress:update")
    public R update(@RequestBody ProgressEntity progress){
		progressService.updateById(progress);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("website_university:progress:delete")
    public R delete(@RequestBody Integer[] ids){
		progressService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
