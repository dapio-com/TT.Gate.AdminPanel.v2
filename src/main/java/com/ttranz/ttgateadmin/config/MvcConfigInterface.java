package com.ttranz.ttgateadmin.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;

public interface MvcConfigInterface {
    void setApplicationContext(ApplicationContext applicationContext) throws BeansException;
}
