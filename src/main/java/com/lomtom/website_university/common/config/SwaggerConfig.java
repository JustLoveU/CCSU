package com.lomtom.website_university.common.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @author: LOMTOM
 * @Date: 2021/2/2
 * @Time: 10:42
 * @Email: lomtom@qq.com
 */

@Configuration
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo());
    }
    /**
     * 配置swagger信息
     */
    public ApiInfo apiInfo(){
        return new ApiInfo(
                "易留学",
                "即使再小的帆也能远航",
                "1.0",
                "urn:tos",
                new Contact("lomtom", "#", "lomtom@qq.com"),
                "Apache 2.0",
                "http://www.apache.org/licenses/LICENSE-2.0",
                new ArrayList<>());
    }
}
