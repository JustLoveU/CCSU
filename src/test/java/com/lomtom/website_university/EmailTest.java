package com.lomtom.website_university;

import com.lomtom.website_university.mail.SendMailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: LOMTOM
 * @Date: 2021/2/2
 * @Time: 15:39
 * @Email: lomtom@qq.com
 */

//@SpringBootTest
public class EmailTest {


    @Test
    public void test(){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date date = new Date(System.currentTimeMillis() - 300000);
        String nowDate = formatter.format(date);
        System.out.println(nowDate);
    }
}
