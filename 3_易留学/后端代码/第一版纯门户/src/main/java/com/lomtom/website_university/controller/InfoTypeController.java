package com.lomtom.website_university.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.R;
import com.lomtom.website_university.entity.InfoTypeEntity;
import com.lomtom.website_university.service.InfoTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.Map;



/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
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
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = infoTypeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		InfoTypeEntity infoType = infoTypeService.getById(id);

        return R.ok().put("infoType", infoType);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody InfoTypeEntity infoType){
		infoTypeService.save(infoType);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody InfoTypeEntity infoType){
		infoTypeService.updateById(infoType);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		infoTypeService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/list1")
    public R list1(@RequestBody  InfoTypeEntity infoTypeEntity){
        List<InfoTypeEntity> list = infoTypeService.list(new QueryWrapper<InfoTypeEntity>().eq("status", infoTypeEntity.getStatus())
                .eq("type", infoTypeEntity.getType()));
        return R.ok().put("code",list.size()==0?404:200).put("list",list);
    }



}
