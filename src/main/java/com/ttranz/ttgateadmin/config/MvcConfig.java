package com.ttranz.ttgateadmin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class MvcConfig implements WebMvcConfigurer {




    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");


    }

}
