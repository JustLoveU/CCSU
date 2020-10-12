package io.renren.main.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.Map;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import io.renren.main.inter.InsertInterface;
import io.renren.main.service.UserService1;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.renren.main.entity.UserEntity;
import io.renren.main.common.utils.PageUtils;
import io.renren.main.common.utils.R;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@RestController
@RequestMapping("website_university/user")
public class UserController {
    @Autowired
    private UserService1 userService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = userService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id, HttpServletRequest request){
		UserEntity user = userService.getById(id);
        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody UserEntity user){
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("username",user.getUsername()));
        if (userEntity!=null){
            return R.ok().put("code",400);
        }
        user.setDate(new Date());
        user.setStatus(true);
        user.setRole("1");
		userService.save(user);
        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		userService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

    /**
     * 登录
     */
    @RequestMapping("/login")
    public R login(@RequestBody  @Validated(InsertInterface.class) UserEntity user, HttpServletRequest request){
        if (user.getPassword() == null|| user.getUsername()==null){
            return R.ok().put("code",40).put("date","用户名或密码不能为空");
        }
        UserEntity user1 = userService.getOne(new QueryWrapper<UserEntity>().eq("username", user.getUsername())
                .eq("password", user.getPassword()).eq("status",true),false);
        if (user1==null){
            return R.ok().put("code",404);
        }
        return R.ok().put("code",200).put("user",user1);
    }



}
