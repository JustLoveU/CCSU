package com.lomtom.website_university.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.lomtom.website_university.common.utils.R;
import com.lomtom.website_university.entity.UserEntity;
import com.lomtom.website_university.inter.InsertInterface;
import com.lomtom.website_university.mail.SendMailService;
import com.lomtom.website_university.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.Objects;


/**
 * 
 *
 * @author lomtom
 * @email lomtom@qq.com
 * @date 2020-07-16 19:41:32
 */
@RestController
@RequestMapping("website_university/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    SendMailService sendMailService;

//    /**
//     * 列表
//     */
//    @RequestMapping("/list")
//    public R list(@RequestParam Map<String, Object> params){
//        PageUtils page = userService.queryPage(params);
//
//        return R.ok().put("page", page);
//    }


    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("获取某个用户")
    public R info(@PathVariable("id") Integer id, HttpServletRequest request){
		UserEntity user = userService.getById(id);
        return R.ok().put("user", user);
    }

    /**
     * 保存
     */
    @PostMapping("/save")
    @ApiOperation("注册用户")
    public R save(@RequestBody UserEntity user,String code){
        Boolean verification = sendMailService.verification(user.getEmail(), code);
        if(!verification){
            return Objects.requireNonNull(R.ok().put("code", 400)).put("msg","请检查验证码");
        }
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
    @PostMapping("/update")
    @ApiOperation("修改用户")
    public R update(@RequestBody UserEntity user){
		userService.updateById(user);

        return R.ok();
    }

//    /**
//     * 删除
//     */
//    @RequestMapping("/delete")
//    public R delete(@RequestBody Integer[] ids){
//		userService.removeByIds(Arrays.asList(ids));
//
//        return R.ok();
//    }

    /**
     * 登录
     */
    @PostMapping("/login")
    @ApiOperation("登录")
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
