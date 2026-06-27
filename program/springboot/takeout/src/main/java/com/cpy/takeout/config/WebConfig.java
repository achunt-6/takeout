package com.cpy.takeout.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/goods/list")
                .excludePathPatterns("/goods/detail")
                .excludePathPatterns("/user/login")
                .excludePathPatterns("/user/register")
                .excludePathPatterns("/user/address/**")
                .excludePathPatterns("/user/uploadAvatar")
                .excludePathPatterns("/user/updateInfo")
                .excludePathPatterns("/address/**")
                .excludePathPatterns("/file/**")
                .excludePathPatterns("/goods/**");

    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/file/**")
                .addResourceLocations("file:" + FileConfig.UPLOAD_PATH + "/");
    }
}