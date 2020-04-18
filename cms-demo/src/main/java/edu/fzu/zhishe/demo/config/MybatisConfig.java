package edu.fzu.zhishe.demo.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author liang on 4/11/2020.
 * @version 1.0
 */
@Configuration
@MapperScan("edu.fzu.zhishe.cms.mapper")
public class MybatisConfig {

}
