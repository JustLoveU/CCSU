package com.lomtom.website_university.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lomtom.website_university.entity.MajorEntity;
import com.lomtom.website_university.service.MajorService;
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
@RequestMapping("website_university/major")
public class MajorController {
    @Autowired
    private MajorService majorService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("website_university:major:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = majorService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("website_university:major:info")
    public R info(@PathVariable("id") Integer id){
		MajorEntity major = majorService.getById(id);

        return R.ok().put("major", major);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("website_university:major:save")
    public R save(@RequestBody MajorEntity major){
		majorService.save(major);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("website_university:major:update")
    public R update(@RequestBody MajorEntity major){
		majorService.updateById(major);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("website_university:major:delete")
    public R delete(@RequestBody Integer[] ids){
		majorService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
