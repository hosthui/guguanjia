package com.lyh.guguanjia.config;


import com.lyh.guguanjia.interceptor.LoginInterceptor;
import com.lyh.guguanjia.interceptor.ResourceInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class springmvcConfig implements WebMvcConfigurer {

	@Autowired
	ResourceInterceptor resourceInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        LoginInterceptor loginInterceptor = new LoginInterceptor();
        registry.addInterceptor(loginInterceptor).addPathPatterns(new String[]{"/manager/**","/**/*.html"}).excludePathPatterns(new String[]{"/login.html","/notauth.html","/notlogin.html"});
	    registry.addInterceptor(resourceInterceptor).addPathPatterns(new String[]{"/manager/**","/**/*.html"}).excludePathPatterns(new String[]{"/login.html","/notauth.html","/notlogin.html"});
    }
}
