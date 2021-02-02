package com.lomtom.website_university.common.config;

/**
 * @Author: LOMTOM
 * @Date: 2020/7/17
 * @Time: 19:47
 * @Email: lomtom@qq.com
 */

import com.baomidou.mybatisplus.extension.plugins.PaginationInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * MyBatis-Plus配置类
 */
@EnableTransactionManagement
@Configuration
//@MapperScan("com.lomtom.website_university")
public class MybatisPlusConfig {
    /**
     * 分页插件
     */
    @Bean
    public PaginationInterceptor paginationInterceptor() {
        return new PaginationInterceptor();
    }
}
