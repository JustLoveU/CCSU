package com.lomtom.website_1.config;

import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @Author: LOMTOM
 * @Date: 2020/9/11
 * @Time: 21:28
 * @Email: lomtom@qq.com
 */


@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception{
//        (172).(([1][6-9])|([2][0-9])|([3][0-1])).
//        ((25[0-5])|(2[0-4][0-9])|(1[0-9][0-9])|([0-9][0-9])|([0-9]))
        httpSecurity.csrf().disable();
        httpSecurity.authorizeRequests()
                .antMatchers("/index.html",
                        "/admin/article/add",
                        "/admin/article/delete/**",
                        "/article/add",
                        "/article/delete/**",
                        "/admin/friend/save",
                        "/admin/friend/update",
                        "/admin/friend/delete",
                        "/friend/save",
                        "/friend/update",
                        "/friend/delete",
                        "/admin/profile/add",
                        "/admin/profile/update",
                        "/admin/profile/delete",
                        "/profile/add",
                        "/profile/update",
                        "/profile/delete",
                        "/admin/user/save",
                        "/admin/user/update",
                        "/admin/user/delete",
                        "/user/save",
                        "/user/update",
                        "/user/delete",
                        "/admin/other/add",
                        "/other/add")
                .access("hasIpAddress('::1')" +
                        " or hasIpAddress('127.0.0.1')" +
                        " or hasIpAddress('10.0.0.0/8')" +
                        " or hasIpAddress('172.16.0.0/12')" +
                        " or hasIpAddress('192.168.0.0/16')" )
                .and()
                .httpBasic();
//        https://segmentfault.com/q/1010000023894786

    }
}
