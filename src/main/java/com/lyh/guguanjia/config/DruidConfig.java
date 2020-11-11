package com.lyh.guguanjia.config;


import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import org.springframework.aop.framework.autoproxy.BeanNameAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DruidConfig {
	@Bean(name="druidStatInterceptor")//设置druid 的 aop切面类
	public DruidStatInterceptor getDruidStatInterceptor(){
		DruidStatInterceptor druidStatInterceptor = new DruidStatInterceptor();

		return druidStatInterceptor;
	}


	@Bean//配置spring监控
	public BeanNameAutoProxyCreator getAutoProxyCreator(){
		BeanNameAutoProxyCreator beanNameAutoProxyCreator = new BeanNameAutoProxyCreator();
		beanNameAutoProxyCreator.setProxyTargetClass(true);
		beanNameAutoProxyCreator.setBeanNames(new String[]{"*Mapper","*ServiceImpl"});
		beanNameAutoProxyCreator.setInterceptorNames("druidStatInterceptor");
		return beanNameAutoProxyCreator;
	}
}
