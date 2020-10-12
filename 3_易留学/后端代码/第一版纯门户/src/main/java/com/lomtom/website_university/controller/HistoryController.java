package com.lomtom.website_university.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lomtom.website_university.entity.HistoryEntity;
import com.lomtom.website_university.service.HistoryService;
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
@RequestMapping("website_university/history")
public class HistoryController {
    @Autowired
    private HistoryService historyService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    //@RequiresPermissions("website_university:history:list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = historyService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("website_university:history:info")
    public R info(@PathVariable("id") Integer id){
		HistoryEntity history = historyService.getById(id);

        return R.ok().put("history", history);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("website_university:history:save")
    public R save(@RequestBody HistoryEntity history){
		historyService.save(history);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("website_university:history:update")
    public R update(@RequestBody HistoryEntity history){
		historyService.updateById(history);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("website_university:history:delete")
    public R delete(@RequestBody Integer[] ids){
		historyService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
