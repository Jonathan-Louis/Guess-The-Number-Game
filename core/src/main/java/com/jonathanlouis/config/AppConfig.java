package com.jonathanlouis.config;

import com.jonathanlouis.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@Import(GameConfig.class)
@ComponentScan(basePackages = "com.jonathanlouis")
public class AppConfig {

    // -- bean methods --


}
