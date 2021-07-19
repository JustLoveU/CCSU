package com.lomtom.website_university.controller;

import com.lomtom.website_university.common.utils.R;
import com.lomtom.website_university.mail.SendMailService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: LOMTOM
 * @Date: 2021/2/2
 * @Time: 15:11
 * @Email: lomtom@qq.com
 */

@RestController
@RequestMapping("website_university/email")
@Api(tags = "邮箱接口")
public class MailController {

    @Autowired
    SendMailService sendMailService;

    @GetMapping("sendCode")
    @ApiOperation("发送验证码")
    public R generateCode(String email){
        sendMailService.sendMail(email);
        return R.ok();
    }

}
