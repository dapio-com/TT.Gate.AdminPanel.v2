package com.ttranz.ttgateadmin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;




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
