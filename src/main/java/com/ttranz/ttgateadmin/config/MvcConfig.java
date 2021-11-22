package com.ttranz.ttgateadmin.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring5.ISpringTemplateEngine;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;

import static org.apache.xmlbeans.XmlOptions.CHARACTER_ENCODING;




@Configuration
public class MvcConfig implements WebMvcConfigurer {


//    private static final String CHARACTER_ENCODING = "UTF-8";
//    private ApplicationContext applicationContext;
//
//    @Override
//    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
//        this.applicationContext = applicationContext;
//    }
//
//    @Bean
//    public ViewResolver javascriptViewResolver() {
//        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
//        resolver.setTemplateEngine((ISpringTemplateEngine) templateEngine(javascriptTemplateResolver()));
//        resolver.setContentType("application/javascript");
//        resolver.setCharacterEncoding(CHARACTER_ENCODING);
//        resolver.setViewNames(new String[] {"*.js"});
//        return resolver;
//    }
//
//    private TemplateEngine templateEngine(ITemplateResolver templateResolver) {
//        SpringTemplateEngine engine = new SpringTemplateEngine();
//        engine.setTemplateResolver(templateResolver);
//        return engine;
//    }
//
//    public ITemplateResolver javascriptTemplateResolver() {
//        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
//        resolver.setApplicationContext(applicationContext);
//        resolver.setPrefix("classpath:/static/js/");
//        resolver.setCacheable(false);
//        resolver.setTemplateMode(TemplateMode.JAVASCRIPT);
//        // resolver.setSuffix(".js");
//        return resolver;
//    }

    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/login").setViewName("login");


    }

}
