package com.dhwooden.ep;

import javafx.application.Application;

import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;


/**
 * Created by kaiwang on 2018/5/21.
 */
public class SpringBootStartApplication extends SpringBootServletInitializer {

    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        // 注意这里要指向原先用main方法执行的Application启动类
        return builder.sources(EquipmentManagerApplication.class);
    }
}
