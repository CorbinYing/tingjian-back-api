package org.corbin.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScans;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
/**
 * 实体类不在本项目中，必须指定
 */
@EntityScan("org.corbin.common.entity")
@EnableJpaAuditing
@ComponentScans(value = {@ComponentScan(value = "org.corbin.common.repository"),
        @ComponentScan(value = "org.corbin.common.service"),
        @ComponentScan(value = "org.corbin.common.base.config")})
public class ClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientApplication.class, args);
    }

}

