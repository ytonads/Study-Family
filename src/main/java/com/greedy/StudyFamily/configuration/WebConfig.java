package com.greedy.StudyFamily.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Value("${file.add-resource-locations}")
    private String ADD_RESOURCE_LOCATION;

    @Value("${file.add-resource-handler}")
    private String ADD_RESOURCE_HANDLER;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
    	
    	log.info("ADD_RESOURCE_LOCATION : {}", ADD_RESOURCE_LOCATION);
    	log.info("ADD_RESOURCE_HANDLER : {}", ADD_RESOURCE_HANDLER);
    	
        registry.addResourceHandler(ADD_RESOURCE_HANDLER)
                .addResourceLocations(ADD_RESOURCE_LOCATION);

    }

}

