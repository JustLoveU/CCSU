package com.lomtom.website_university.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lomtom.website_university.entity.UniversityEntity;
import com.lomtom.website_university.service.UniversityService;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.R;



/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-08-28 12:18:04
 */
@RestController
@RequestMapping("website_university/university")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("website_university:university:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = universityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("website_university:university:info")
    public R info(@PathVariable("id") Integer id){
		UniversityEntity university = universityService.getById(id);

        return R.ok().put("university", university);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("website_university:university:save")
    public R save(@RequestBody UniversityEntity university){
		universityService.save(university);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("website_university:university:update")
    public R update(@RequestBody UniversityEntity university){
		universityService.updateById(university);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("website_university:university:delete")
    public R delete(@RequestBody Integer[] ids){
		universityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
