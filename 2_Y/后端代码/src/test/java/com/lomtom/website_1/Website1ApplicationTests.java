package com.lomtom.website_1;

import org.junit.jupiter.api.Test;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//@SpringBootTest
class Website1ApplicationTests {

    @Test
    void contextLoads() {
        //        192.168.1.0-192.168.1.255
        String regex ="^(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.)" +
                "{3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))-(?=(\\b|\\D))(((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))\\.)" +
                "{3}((\\d{1,2})|(1\\d{1,2})|(2[0-4]\\d)|(25[0-5]))(?=(\\b|\\D))$";
        String s = "192.168.1.222";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        if (m.matches()) {
            System.out.println("匹配");
        } else {
            System.out.println("不匹配");
        }
    }

}
