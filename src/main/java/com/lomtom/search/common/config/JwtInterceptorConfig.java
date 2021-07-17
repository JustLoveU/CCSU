package com.lomtom.search.common.config;

import com.lomtom.search.common.filter.AdminAuthorizeFilter;
import com.lomtom.search.common.filter.UserAuthorizeFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/13
 * @Time: 12:06
 */

@Configuration
public class JwtInterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        //默认拦截所有路径
        registry.addInterceptor(adminAuthenticationInterceptor())
                .addPathPatterns("/auth/**");
        registry.addInterceptor(userAuthenticationInterceptor())
                .addPathPatterns("/auth1/**");
    }

    @Bean
    public AdminAuthorizeFilter adminAuthenticationInterceptor() {
        return new AdminAuthorizeFilter();
    }

    @Bean
    public UserAuthorizeFilter userAuthenticationInterceptor() {
        return new UserAuthorizeFilter();
    }
}