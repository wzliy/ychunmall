package com.ychun.common.properties;

import lombok.Data;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.validation.annotation.Validated;


@ConfigurationProperties(prefix = "swagger")
@Validated
@Getter
@Setter
public class SwaggerProperties {

    @NonNull
    private String description;

    @NonNull
    private String title;

    @NonNull
    private String serviceUrl;

    @NonNull
    private String contactName;

    @NonNull
    private String contactUrl;

    @NonNull
    private String contactEmail;

    @NonNull
    private String apiVersion;

    @NonNull
    private String groupName;

    @NonNull
    private String basePackage;
}
