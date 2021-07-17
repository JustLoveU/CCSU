package com.lomtom.search.main.controller;

import com.lomtom.search.common.pojo.Result;
import com.lomtom.search.common.util.HttpStatus;
import com.lomtom.search.main.service.SendMailService;
import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 19:56
 */

@Api(tags = "邮箱接口")
@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private SendMailService sendMailService;

    @ApiOperation("发送验证码")
    @GetMapping("/send")
    public Result send(@RequestParam("email") String email){
        if (StringUtil.isNullOrEmpty(email)){
            return Result.error(HttpStatus.USER_EMAIL_EMPTY);
        }
        sendMailService.sendMail(email);
        return Result.ok();
    }
}
