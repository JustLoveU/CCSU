package com.lomtom.search.common.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.oas.annotations.EnableOpenApi;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;

/**
 * @Author: LOMTOM
 * @Date: 2021/4/12
 * @Time: 19:52
 */

@EnableOpenApi
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket docket(Environment environment){
        return new Docket(DocumentationType.OAS_30)
                .apiInfo(apiInfo())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.lomtom.search.main.controller"))
                .build();
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
