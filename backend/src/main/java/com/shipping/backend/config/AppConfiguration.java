package com.shipping.backend.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration @Setter @Getter
@ConfigurationProperties(prefix = "ui-message")
public class AppConfiguration {

    private String  packageTypes;
    private String  packageSizes;
    private String  transportTypes;
    private String  transportVelocity;
    private String  cities;

}

