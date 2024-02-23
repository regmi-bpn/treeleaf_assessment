package com.example.treeleaf_assessment.commons;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Getter
@Setter
@Configuration
@ConfigurationProperties(prefix = "security")
public class SecurityProperty {
    private List<String> corsAllowedOrigins;
    private List<String> corsAllowedMethods;
    private List<String> permitUrls;
}
