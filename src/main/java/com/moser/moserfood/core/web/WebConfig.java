package com.moser.moserfood.core.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.ShallowEtagHeaderFilter;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;

/**
 * @author Juliano Moser
 */
@Configuration
public class WebConfig implements WebMvcConfigurer {
    //
//    @Autowired
//    private ApiDeprecationHandler apiDeprecationHandler;
    @Autowired
    private ApiRetirementHandler apiRetirementHandler;

    @Bean
    public Filter shallowEtagHeader() {
        return new ShallowEtagHeaderFilter();
    }

//    @Override
//    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(apiDeprecationHandler);
//    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(apiRetirementHandler);
    }

    //
//    @Override
//    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
//        configurer.defaultContentType(MoserMediaTypes.V2_APPLICATION_JSON);
//    }
}
