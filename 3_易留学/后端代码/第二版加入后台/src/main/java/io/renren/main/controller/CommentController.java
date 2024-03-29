package io.renren.main.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.R;
import io.renren.main.entity.CommentAndUserEntity;
import io.renren.main.entity.CommentEntity;
import io.renren.main.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("website_university/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commentService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		CommentEntity comment = commentService.getById(id);

        return R.ok().put("comment", comment);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CommentEntity comment){
        comment.setTime(new Date());
		commentService.save(comment);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CommentEntity comment){
		commentService.updateById(comment);
        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		commentService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }


    /**
     *
     * @param id
     * @param params
     * @return
     */
    @RequestMapping("/ListByUniversity/{id}")
    public R ListByUniversity(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, HttpServletRequest request){
        IPage<CommentAndUserEntity> page = commentService.queryPage(params,id);
        return R.ok().put("page", page);
    }


    /**
     *
     * @param id
     * @param params
     * @return
     */
    @RequestMapping("/ListByUser/{id}")
    public R ListByUser(@PathVariable("id") Integer id, @RequestParam Map<String, Object> params, HttpServletRequest request){
        IPage<CommentEntity> page = commentService.queryPageByUser(params,id);
        return R.ok().put("page", page);
    }


}
