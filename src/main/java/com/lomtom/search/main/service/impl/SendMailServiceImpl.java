package com.lomtom.search.main.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.search.common.util.GenerateCode;
import com.lomtom.search.common.util.HttpStatus;
import com.lomtom.search.main.entity.Verification;
import com.lomtom.search.main.mapper.VerificationMapper;
import com.lomtom.search.main.service.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 19:58
 */

@DS("slave")
@Service
public class SendMailServiceImpl extends ServiceImpl<VerificationMapper, Verification> implements SendMailService {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private VerificationMapper verificationMapper;

    /**
     * 异步请求
     *
     * @param email
     * @return
     */
    @Override
    @Async
    public boolean sendMail(String email) {
        //初始化邮件信息类
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setSubject("lomtom | 验证码");
        simpleMailMessage.setFrom("lomtom@qq.com");
        simpleMailMessage.setTo(email);
        //获取验证码
        String verification = GenerateCode.generateCode();
        //将验证码存放进邮箱
        simpleMailMessage.setText("将在5分钟后过期，请尽快输入验证码:" + verification);
        verificationMapper.insert(new Verification(verification, email));
        javaMailSender.send(simpleMailMessage);
        return true;
    }

    /**
     * 验证用户信息
     *
     * @param email
     * @param code
     * @return
     *
     * 404 没有验证码/验证码不正确/邮箱不正确
     * 400 验证码过期
     */
    @Override
    public int verification(String email, String code) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis() - 300000);
        String nowDate = formatter.format(date);
        Verification verification = baseMapper.selectOne(new QueryWrapper<Verification>()
                .eq("code", code).eq("email", email).ge("date", nowDate).last(" limit 1"));
        if (verification == null){
            return HttpStatus.VERIFICATION_EXCEPTION.getCode();
        }
        return 200;
    }
}
