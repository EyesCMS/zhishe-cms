package edu.fzu.zhishe.demo.config;

import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import edu.fzu.zhishe.cms.model.CmsClubDO;
import edu.fzu.zhishe.demo.dto.CmsClubDTO;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author liang on 4/13/2020.
 */
@Configuration
public class DozerBeanMapperConfig {

    @Bean
    public Mapper mapper() {
        Mapper mapper = DozerBeanMapperBuilder.create()
            .withMappingBuilder(beanMappingBuilder()).build();
        return mapper;
    }

    @Bean
    public BeanMappingBuilder beanMappingBuilder() {
        return new BeanMappingBuilder() {
            @Override
            protected void configure() {
                // 个性化配置添加在此
                mapping(CmsClubDTO.class, CmsClubDO.class)
                    .fields("userId", "chiefId")
                    .fields("clubName", "name")
                    .exclude("applicant")
                    .exclude("reason")
                    .exclude("handleAt")
                    .exclude("state");
            }
        };
    }
}
