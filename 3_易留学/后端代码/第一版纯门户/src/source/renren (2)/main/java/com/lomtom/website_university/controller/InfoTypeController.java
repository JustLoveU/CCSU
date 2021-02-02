package com.lomtom.website_university.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lomtom.website_university.entity.InfoTypeEntity;
import com.lomtom.website_university.service.InfoTypeService;
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
@RequestMapping("website_university/infotype")
public class InfoTypeController {
    @Autowired
    private InfoTypeService infoTypeService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("website_university:infotype:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = infoTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("website_university:infotype:info")
    public R info(@PathVariable("id") Integer id){
		InfoTypeEntity infoType = infoTypeService.getById(id);

        return R.ok().put("infoType", infoType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("website_university:infotype:save")
    public R save(@RequestBody InfoTypeEntity infoType){
		infoTypeService.save(infoType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("website_university:infotype:update")
    public R update(@RequestBody InfoTypeEntity infoType){
		infoTypeService.updateById(infoType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("website_university:infotype:delete")
    public R delete(@RequestBody Integer[] ids){
		infoTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
