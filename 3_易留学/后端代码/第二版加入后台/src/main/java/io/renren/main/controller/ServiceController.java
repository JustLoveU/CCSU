package io.renren.main.controller;

import java.util.Arrays;
import java.util.Map;

import io.renren.main.service.ServiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.main.entity.ServiceEntity;
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
