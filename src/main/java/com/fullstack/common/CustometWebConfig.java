package com.fullstack.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.fullstack.config.JwtInterceptor;

@Configuration
public class CustometWebConfig extends WebMvcConfigurerAdapter {

	@Autowired
	JwtInterceptor interceptor;

	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(interceptor);

	}

}
