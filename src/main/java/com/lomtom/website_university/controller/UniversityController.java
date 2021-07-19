package com.lomtom.website_university.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.lomtom.website_university.entity.CommentAndUserEntity;
import com.lomtom.website_university.entity.InfoEntity;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.lomtom.website_university.entity.UniversityEntity;
import com.lomtom.website_university.service.UniversityService;
import com.lomtom.website_university.common.utils.PageUtils;
import com.lomtom.website_university.common.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@RestController
@RequestMapping("website_university/university")
@Api(tags = "院校接口")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    /**
     * 列表
     */
    @GetMapping("/list")
    @ApiOperation("分页获取院校")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = universityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("获取某个院校信息")
    public R info(@PathVariable("id") Integer id){
		UniversityEntity university = universityService.getById(id);

        return R.ok().put("university", university);
    }

//    /**
//     * 保存
//     */
//    @RequestMapping("/save")
//    public R save(@RequestBody UniversityEntity university){
//		universityService.save(university);
//
//        return R.ok();
//    }
//
//    /**
//     * 修改
//     */
//    @RequestMapping("/update")
//    public R update(@RequestBody UniversityEntity university){
//		universityService.updateById(university);
//
//        return R.ok();
//    }
//
//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Integer[] ids){
//		universityService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

    @GetMapping("/listLimit/{number}")
    @ApiOperation("获取number个top大学")
    public R list(@PathVariable("number") Integer number){
        List<UniversityEntity> list = universityService.list(new QueryWrapper<UniversityEntity>().orderBy(true,false,"view").last(" limit "+number));
        return R.ok().put("code",list.size()==0?404:200).put("list",list);
    }

    @GetMapping("/list1")
    @ApiOperation("获取3个top大学")
    public R list1(){
        List<UniversityEntity> list = universityService.list(new QueryWrapper<UniversityEntity>().orderBy(true,false,"view").last(" limit 3"));
        return R.ok().put("code",list.size()==0?404:200).put("list",list);
    }


    @GetMapping("/list2")
    @ApiOperation("获取6个top大学")
    public R list2(){
        List<UniversityEntity> list = universityService.list(new QueryWrapper<UniversityEntity>().orderBy(true,false,"view").last(" limit 6"));
        return R.ok().put("code",list.size()==0?404:200).put("list",list);
    }




    /**
     *
     * @param id
     * @param params
     * @return
     */
    @GetMapping("/ListByUser/{id}")
    @ApiOperation("获取用户大学收藏列表")
    public R ListByUniversity(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params){
        IPage<UniversityEntity> page = universityService.queryPage(params,id);
        return R.ok().put("page", page);
    }


    @GetMapping("/search")
    @ApiOperation("搜索6个院校")
    public R ListBySearch(@RequestParam("search")  String search){
        List<UniversityEntity> list =  universityService.list(new QueryWrapper<UniversityEntity>().like("name",search).last(" limit 6"));
        return R.ok().put("list",list);
    }


    @GetMapping("/search1")
    @ApiOperation("搜索院校")
    public R ListBySearch1(@RequestParam("search")  String search){
        List<UniversityEntity> list =  universityService.list(new QueryWrapper<UniversityEntity>().like("name",search));
        return R.ok().put("list",list);
    }

}
