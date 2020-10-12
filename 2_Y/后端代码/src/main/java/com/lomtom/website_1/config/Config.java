package com.lomtom.website_1.config;

import jdk.nashorn.internal.objects.annotations.Getter;
import lombok.Data;
import lombok.ToString;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * @Author: LOMTOM
 * @Date: 2020/9/23
 * @Time: 18:21
 * @Email: lomtom@qq.com
 */
@Component
@Data
@Configuration
@ConfigurationProperties(prefix = "yml")
public class Config {
    private HashMap<Integer, String> maps;
}


