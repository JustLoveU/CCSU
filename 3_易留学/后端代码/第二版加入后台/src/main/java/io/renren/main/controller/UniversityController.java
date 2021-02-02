package io.renren.main.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.main.service.UniversityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.main.entity.UniversityEntity;
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
@RequestMapping("website_university/university")
public class UniversityController {
    @Autowired
    private UniversityService universityService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = universityService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		UniversityEntity university = universityService.getById(id);

        return R.ok().put("university", university);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UniversityEntity university){
		universityService.save(university);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UniversityEntity university){
		universityService.updateById(university);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		universityService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    @RequestMapping("/list1")
    public R list1(){
        List<UniversityEntity> list = universityService.list(new QueryWrapper<UniversityEntity>().orderBy(true,false,"view").last(" limit 3"));
        return R.ok().put("code",list.size()==0?404:200).put("list",list);
    }


    @RequestMapping("/list2")
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
    @RequestMapping("/ListByUser/{id}")
    public R ListByUniversity(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params){
        IPage<UniversityEntity> page = universityService.queryPage(params,id);
        return R.ok().put("page", page);
    }


    @RequestMapping("/search")
    public R ListBySearch(@RequestParam("search")  String search){
        List<UniversityEntity> list =  universityService.list(new QueryWrapper<UniversityEntity>().like("name",search).last(" limit 6"));
        return R.ok().put("list",list);
    }


    @RequestMapping("/search1")
    public R ListBySearch1(@RequestParam("search")  String search){
        List<UniversityEntity> list =  universityService.list(new QueryWrapper<UniversityEntity>().like("name",search));
        return R.ok().put("list",list);
    }

}
