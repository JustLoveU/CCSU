package com.lomtom.website_university.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.lomtom.website_university.entity.InfoTypeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.lomtom.website_university.entity.InfoEntity;
import com.lomtom.website_university.service.InfoService;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.R;



/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@RestController
@RequestMapping("website_university/info")
public class InfoController {
    @Autowired
    private InfoService infoService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = infoService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		InfoEntity info = infoService.getById(id);
		infoService.update(new UpdateWrapper<InfoEntity>().setSql(true,"view = view + 1"));
        return R.ok().put("info", info);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody InfoEntity info){
		infoService.save(info);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody InfoEntity info){
		infoService.updateById(info);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		infoService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    @RequestMapping("/list1")
    public R list1(){
        List<InfoEntity> list = infoService.list(new QueryWrapper<InfoEntity>().orderBy(true,false,"view").last(" limit 6"));
        return R.ok().put("code",list.size()==0?404:200).put("list",list);
    }


    /**
     * 列表
     */
    @RequestMapping("/list2")
    public R list2(@RequestParam Map<String, Object> params,@RequestParam("id") Integer id){
        PageUtils page = infoService.queryPage(params,id);
        return R.ok().put("page", page);
    }



    /**
     * 列表
     */
    @RequestMapping("/infoSearch")
    public R infoSearch(@RequestParam Map<String, Object> params,@RequestParam("search") String search){
        PageUtils page = infoService.queryPage(params,search);
        return R.ok().put("page", page);
    }


    @RequestMapping("/ListByUniversity/{id}")
    public R ListByUniversity(@PathVariable("id") Integer id){
        List<InfoEntity> list = infoService.list(new QueryWrapper<InfoEntity>().eq("university_id",id)
//                .eq("type_id",4)
                .orderBy(true,false,"view").last(" limit 6"));
        return R.ok().put("code",list.size()==0?404:200).put("list",list);
    }

}
