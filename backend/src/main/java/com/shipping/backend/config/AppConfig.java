package com.shipping.backend.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

    @Value("${ui-url-request.package-types}")
    private String packageTypes;

    public String getPackageTypes() {
        return packageTypes;
    }
}
