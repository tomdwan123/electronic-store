package com.be.electronic_store.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@ComponentScan(basePackages = { "com.be.electronic_store" })
@PropertySource("classpath:test.environment.properties")
public class TestConfig {
}