package com.adarrivi.multi.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableAsync;

@Configuration
@EnableAsync
@ComponentScan(basePackages = "com.adarrivi.multi.core")
public class ApplicationConfiguration {

    // No beans to declare
}
