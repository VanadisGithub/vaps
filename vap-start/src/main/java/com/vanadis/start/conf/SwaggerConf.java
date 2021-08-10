package com.vanadis.start.conf;

import com.google.common.base.Predicates;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:44
 */
@Configuration
@EnableSwagger2
public class SwaggerConf {

    @Bean
    public Docket vap() {
        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .paths(Predicates.not(PathSelectors.regex("/error.*|/actuator.*")))
            //.paths(PathSelectors.regex("/.*"))
            .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Vanadis'API-DOCs")
                .description("Vanadis'API-DOCs")
                .termsOfServiceUrl("https://github.com/VanadisGithub/vap")
                .version("1.0").build();
    }
}
