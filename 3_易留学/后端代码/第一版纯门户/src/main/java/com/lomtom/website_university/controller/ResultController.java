package com.lomtom.website_university.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.R;
import com.lomtom.website_university.entity.ApplyEntity;
import com.lomtom.website_university.entity.ResultEntity;
import com.lomtom.website_university.entity.UniversityEntity;
import com.lomtom.website_university.service.ApplyService;
import com.lomtom.website_university.service.ResultService;
import com.lomtom.website_university.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;


/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@RestController
@RequestMapping("website_university/result")
public class ResultController {
    @Autowired
    private ResultService resultService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private UniversityService universityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = resultService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    //@RequiresPermissions("website_university:apply:info")
    public R info(@PathVariable("id") Integer id){
        ResultEntity apply = resultService.getById(id);

        return R.ok().put("apply", apply);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    //@RequiresPermissions("website_university:apply:save")
    public R save(@RequestBody ResultEntity apply){
		resultService.save(apply);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    //@RequiresPermissions("website_university:apply:update")
    public R update(@RequestBody ResultEntity apply){
		resultService.updateById(apply);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    //@RequiresPermissions("website_university:apply:delete")
    public R delete(@RequestBody Integer[] ids){
		resultService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    @RequestMapping("/getOneByApplyId/{id}")
    public R isHave(@PathVariable("id") Integer id){
        ResultEntity resultEntity = resultService.getOne(new QueryWrapper<ResultEntity>().eq("apply_id",id).last(" order by id desc limit 1"));
        if (resultEntity!=null){
            ApplyEntity applyEntity = applyService.getById(resultEntity.getApplyId());
            UniversityEntity universityEntity1 = universityService.getById(resultEntity.getUniversityId1());
            UniversityEntity universityEntity2 = universityService.getById(resultEntity.getUniversityId2());
            UniversityEntity universityEntity3 = universityService.getById(resultEntity.getUniversityId3());
            return R.ok().put("code",200).put("result",resultEntity).put("apply",applyEntity).put("university1",universityEntity1).put("university2",universityEntity2).put("university3",universityEntity3);
        }
        return R.ok().put("code",404);
    }
}
