package com.lyh.guguanjia.config;


import com.lyh.guguanjia.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class springmvcConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor).addPathPatterns(new String[]{"/manager/**","*.html"}).excludePathPatterns(new String[]{"/login.html","/notauth.html","/notlogin.html"});
    }
}
