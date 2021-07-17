package com.lomtom.search.main.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.api.R;
import com.lomtom.search.common.group.AddGroup;
import com.lomtom.search.common.util.Constant;
import com.lomtom.search.common.util.JwtUtil;
import com.lomtom.search.main.entity.UserEntity;
import com.lomtom.search.common.pojo.Result;
import com.lomtom.search.common.util.HttpStatus;
import com.lomtom.search.main.form.LoginForm;
import com.lomtom.search.main.form.RegisterForm;
import com.lomtom.search.main.service.SendMailService;
import com.lomtom.search.main.service.UserService;
import com.lomtom.search.main.service.UserTokenService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import xyz.erupt.upms.util.MD5Utils;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.UUID;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 19:44
 */

@Api(tags = "用户接口")
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private SendMailService sendMailService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserTokenService userTokenService;

    @ApiOperation("登录")
    @PostMapping("/login")
    public Result login(@RequestBody  @Validated LoginForm loginForm){
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>()
                .eq("username",loginForm.getUsername()).or()
                .eq("email",loginForm.getUsername()));
        if (userEntity == null){
            return Result.error(HttpStatus.USER_LOGIN_FAIL);
        }
        if (!MD5Utils.digest(loginForm.getPassword()).equals(userEntity.getPassword())){
            return Result.error(HttpStatus.USER_LOGIN_FAIL);
        }
        if (!userEntity.getStatus()){
            return Result.error(HttpStatus.USER_UN_ACTIVE);
        }
        String jwt = userTokenService.createToken(userEntity.getId());
        userEntity.setPassword("");
        return Result.ok(HttpStatus.USER_LOGIN_SUCCESS).put("token",jwt).put("user",userEntity);
    }

    @ApiOperation("注册")
    @PostMapping("/register")
    public Result register(@RequestBody @Validated(AddGroup.class) RegisterForm registerForm){
        int verification = sendMailService.verification(registerForm.getEmail(), registerForm.getCode());
        if (verification == HttpStatus.VERIFICATION_EXCEPTION.getCode()){
            return Result.error(HttpStatus.VERIFICATION_EXCEPTION);
        }
        UserEntity userEntity = userService.getOne(new QueryWrapper<UserEntity>().eq("username",registerForm.getUsername()));
        if (userEntity != null){
            return Result.error(HttpStatus.USER_USERNAME_EXIST);
        }
        UserEntity userEntity1 = userService.getOne(new QueryWrapper<UserEntity>().eq("email",registerForm.getEmail()));
        if (userEntity1 != null){
            return Result.error(HttpStatus.USER_EMAIL_EXIST);
        }
        UserEntity user = new UserEntity();
        user.setEmail(registerForm.getEmail());
        user.setUsername(registerForm.getUsername());
        user.setPassword(MD5Utils.digest(registerForm.getPassword()));
        user.setDate(new Date());
        user.setStatus(true);
        user.setRole("1");
        userService.save(user);
        return Result.ok(HttpStatus.USER_REGISTER_SUCCESS);
    }

    /**
     * 信息
     */
    @GetMapping("/info/{id}")
    @ApiOperation("获取某个用户")
    public Result info(@PathVariable("id") Integer id){
        UserEntity user = userService.getById(id);
        user.setPassword("");
        return Result.ok().put("user", user);
    }

    /**
     * 退出
     */
    @ApiOperation("注销接口")
    @PostMapping("/logout")
    public Result logout(@RequestHeader String token) {
        userTokenService.logout(token);
        return Result.ok();
    }


}
