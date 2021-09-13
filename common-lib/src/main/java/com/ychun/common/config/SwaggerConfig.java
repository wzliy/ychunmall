package com.ychun.common.config;

import com.ychun.common.properties.SwaggerProperties;
import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;

import java.util.function.Predicate;

@Configuration
@EnableSwagger2WebMvc
@EnableConfigurationProperties({SwaggerProperties.class})
public class SwaggerConfig {

    @Bean(value = "defaultApi2")
    public Docket api(SwaggerProperties properties) {
        String[] packages = StringUtils.split(properties.getBasePackage(), ",; ");
        Predicate<RequestHandler> predicate = null;
        for (String packageName : packages) {
            if (predicate == null) {
                predicate = RequestHandlerSelectors.basePackage(packageName);
            } else {
                predicate = predicate.or(RequestHandlerSelectors.basePackage(packageName));
            }
        }
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName(properties.getGroupName())
                .select()
                .apis(predicate)
                .paths(PathSelectors.any())
                .build()
                .apiInfo(apiInfo(properties))
                .useDefaultResponseMessages(false);
    }

    private ApiInfo apiInfo(SwaggerProperties properties) {
        return new ApiInfoBuilder()
                .title(properties.getTitle())
                .description(properties.getDescription())
                .termsOfServiceUrl(properties.getServiceUrl())
                .contact(new Contact(properties.getContactName(), properties.getContactUrl(), properties.getContactEmail()))
                .version(properties.getApiVersion())
                .build();
    }
}
