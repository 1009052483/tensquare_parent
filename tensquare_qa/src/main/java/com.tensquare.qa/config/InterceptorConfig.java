package com.tensquare.qa.config;

import com.tensquare.qa.interceptor.Jwtinterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * Created by XSH on 2018/12/6.
 */
@Configuration
public class InterceptorConfig extends WebMvcConfigurationSupport {

    @Autowired
    private Jwtinterceptor jwtinterceptor;

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(jwtinterceptor)
                .addPathPatterns("/**")
                .excludePathPatterns("/**/login/**");
    }
}
