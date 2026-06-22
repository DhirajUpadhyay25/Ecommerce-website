package com.ecommerce.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        String basePath = System.getProperty("user.dir") + "/uploads/";

        registry.addResourceHandler("/category_img/**")
                .addResourceLocations("file:" + basePath + "category_img/");

        registry.addResourceHandler("/product_img/**")
                .addResourceLocations("file:" + basePath + "product_img/");
    }
}