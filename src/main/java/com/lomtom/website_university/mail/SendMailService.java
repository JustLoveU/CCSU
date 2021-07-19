package com.lomtom.website_university.mail;

import com.baomidou.mybatisplus.extension.service.IService;
import com.lomtom.website_university.entity.Verification;

/**
 * User: 欧阳隆桐
 * Date: 2020/2/16
 * Time: 16:47
 */
public interface SendMailService extends IService<Verification> {
    boolean sendMail(String emailAddress);

    Boolean verification(String username,String Verification);
}
