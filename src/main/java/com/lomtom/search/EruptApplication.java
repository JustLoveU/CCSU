package com.lomtom.search;

import com.xpand.starter.canal.annotation.EnableCanalClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import xyz.erupt.core.annotation.EruptScan;

/**
 * @author LOMTOM
 */
@SpringBootApplication
@EnableCanalClient
@ComponentScan({"xyz.erupt", "com.lomtom.search"})
@EntityScan({"xyz.erupt", "com.lomtom.search"})
@EruptScan({"xyz.erupt", "com.lomtom.search"})
public class EruptApplication {

    public static void main(String[] args) {
        SpringApplication.run(EruptApplication.class, args);
        System.setProperty("java.awt.headless", "false");
    }

}
