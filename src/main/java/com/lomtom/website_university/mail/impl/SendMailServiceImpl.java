package com.lomtom.website_university.mail.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.lomtom.website_university.common.utils.GenerateCode;
import com.lomtom.website_university.dao.VerificationDao;
import com.lomtom.website_university.entity.Verification;
import com.lomtom.website_university.mail.SendMailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User: 欧阳隆桐
 * Date: 2020/1/31
 * Time: 18:41
 */
@Service
public class SendMailServiceImpl extends ServiceImpl<VerificationDao, Verification> implements SendMailService {

    @Autowired
    JavaMailSender javaMailSender;

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
        System.out.println(verification + email);
        baseMapper.insert(new Verification(verification, email));
        javaMailSender.send(simpleMailMessage);
        return true;
    }

    /**
     * 验证用户信息
     *
     * @param email
     * @param code
     * @return
     */
    @Override
    public Boolean verification(String email, String code) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis() - 300000);
        String nowDate = formatter.format(date);
        Verification verification = baseMapper.selectOne(new QueryWrapper<Verification>()
                .eq("code", code).eq("email", email).ge("date", nowDate).last(" limit 1"));
        return verification != null;
    }
}