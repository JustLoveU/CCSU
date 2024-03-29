package com.lomtom.website_university.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lomtom.website_university.entity.ServiceEntity;
import com.lomtom.website_university.service.ServiceService;
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
@RequestMapping("website_university/service")
public class ServiceController {
    @Autowired
    private ServiceService serviceService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("website_university:service:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = serviceService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("website_university:service:info")
    public R info(@PathVariable("id") Integer id){
		ServiceEntity service = serviceService.getById(id);

        return R.ok().put("service", service);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("website_university:service:save")
    public R save(@RequestBody ServiceEntity service){
		serviceService.save(service);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("website_university:service:update")
    public R update(@RequestBody ServiceEntity service){
		serviceService.updateById(service);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("website_university:service:delete")
    public R delete(@RequestBody Integer[] ids){
		serviceService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
