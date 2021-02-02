package io.renren.main.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import io.renren.main.entity.ApplyEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.main.service.ApplyService;
import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.R;



/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@RestController
@RequestMapping("website_university/apply")
public class ApplyController {
    @Autowired
    private ApplyService applyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = applyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("website_university:apply:info")
    public R info(@PathVariable("id") Integer id){
		ApplyEntity apply = applyService.getById(id);

        return R.ok().put("apply", apply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("website_university:apply:save")
    public R save(@RequestBody ApplyEntity apply){
        apply.setApplyDate(new Date());
		applyService.save(apply);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("website_university:apply:update")
    public R update(@RequestBody ApplyEntity apply){
		applyService.updateById(apply);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("website_university:apply:delete")
    public R delete(@RequestBody Integer[] ids){
		applyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
