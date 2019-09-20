package com.shipping.backend.config;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
public class AppConfiguration {

    @Value("${ui-message.package-types}")
    private String  packageTypes;

    @Value("${ui-message.package-sizes}")
    private String  packageSizes;

    @Value("${ui-message.transports.type}")
    private String  transportTypes;

    @Value("${ui-message.transports.velocity}")
    private String  transportVelocity;

    @Value("${ui-message.cities}")
    private String  cities;
}

