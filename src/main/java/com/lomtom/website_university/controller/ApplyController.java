package com.lomtom.website_university.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;
import java.util.Random;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lomtom.website_university.entity.ResultEntity;
import com.lomtom.website_university.entity.UniversityEntity;
import com.lomtom.website_university.service.ResultService;
import com.lomtom.website_university.service.UniversityService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lomtom.website_university.entity.ApplyEntity;
import com.lomtom.website_university.service.ApplyService;
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
@RequestMapping("website_university/apply")
@Api(tags = "申请接口")
public class ApplyController {
    @Autowired
    private ResultService resultService;
    @Autowired
    private ApplyService applyService;
    @Autowired
    private UniversityService universityService;

//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = applyService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }
//
//
//    /**
//     * 信息
//     */
//    @RequestMapping("/info/{id}")
//    //@RequiresPermissions("website_university:apply:info")
//    public R info(@PathVariable("id") Integer id){
//		ApplyEntity apply = applyService.getById(id);
//
//        return R.ok().put("apply", apply);
//    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("保存DIY")
    public R save(@RequestBody ApplyEntity apply){
        ApplyEntity applyEntity = applyService.getOne(new QueryWrapper<ApplyEntity>()
                .eq("user_id",apply.getUserId())
                .eq("address",apply.getAddress())
                .eq("degree",apply.getDegree())
                .eq("years",apply.getYears())
                .eq("major",apply.getMajor())
                .eq("outside_date",apply.getOutsideDate())
                .eq("university",apply.getUniversity())
                .eq("score",apply.getScore())
                .eq("tel",apply.getTel()));
        if (applyEntity==null){
            apply.setApplyDate(new Date());
            applyService.save(apply);
            applyEntity = applyService.getOne(new QueryWrapper<ApplyEntity>()
                    .eq("user_id",apply.getUserId())
                    .eq("address",apply.getAddress())
                    .eq("degree",apply.getDegree())
                    .eq("years",apply.getYears())
                    .eq("major",apply.getMajor())
                    .eq("outside_date",apply.getOutsideDate())
                    .eq("university",apply.getUniversity())
                    .eq("score",apply.getScore())
                    .eq("tel",apply.getTel()));
            ResultEntity resultEntity = new ResultEntity();
            resultEntity.setUserId(apply.getUserId());
            resultEntity.setApplyId(applyEntity.getId());
            Random random = new Random();
            resultEntity.setRate(random.nextInt(15) + 80);
            resultEntity.setUniversityId1(universityService.getOne(new QueryWrapper<UniversityEntity>().last("ORDER BY  RAND() LIMIT 1")).getId());
            resultEntity.setUniversityId2(universityService.getOne(new QueryWrapper<UniversityEntity>().last("ORDER BY  RAND() LIMIT 1")).getId());
            resultEntity.setUniversityId3(universityService.getOne(new QueryWrapper<UniversityEntity>().last("ORDER BY  RAND() LIMIT 1")).getId());
            resultEntity.setLanguage(random.nextInt(3) + 2);
            resultEntity.setNumber(random.nextInt(3) + 2);
            resultEntity.setFee(random.nextInt(3) + 2);
            resultEntity.setTime(random.nextInt(3) + 2);
            resultEntity.setMajor(random.nextInt(3) + 2);
            resultService.save(resultEntity);
        }
        return R.ok().put("apply",applyEntity);
    }

    /**
     * 修改
     */
    @PostMapping("/update")
    @ApiOperation("修改申请")
    public R update(@RequestBody ApplyEntity apply){
		applyService.updateById(apply);
        return R.ok();
    }

//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Integer[] ids){
//		applyService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

    @GetMapping("/isHave/{id}")
    @ApiOperation("查看该用户是否存在申请")
    public R isHave(@PathVariable("id") Integer id){
        ApplyEntity applyEntity = applyService.getOne(new QueryWrapper<ApplyEntity>().eq("user_id",id).last(" order by id desc limit 1"));
        return R.ok().put("code",applyEntity==null?404:200).put("apply",applyEntity);
    }



}
