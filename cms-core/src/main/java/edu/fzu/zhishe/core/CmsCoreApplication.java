package edu.fzu.zhishe.core;

import edu.fzu.zhishe.core.config.StorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author liang on 4/11/2020.
 * @version 1.0
 */
@SpringBootApplication
@ComponentScan(basePackages = "edu.fzu.zhishe")
@EnableConfigurationProperties(StorageProperties.class)
public class CmsCoreApplication {

    public static void main(String[] args) {
        SpringApplication.run(CmsCoreApplication.class, args);
    }
}
