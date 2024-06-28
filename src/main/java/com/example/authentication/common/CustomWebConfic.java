package com.example.authentication.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.example.authentication.Dto.RequestDto;
import com.example.authentication.confic.JwtInterceptor;

@Configuration
public class CustomWebConfic implements WebMvcConfigurer {

	@Autowired
	JwtInterceptor jwtInterceptor;
	
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		registry.addInterceptor(jwtInterceptor);
		
	}
	
	@Bean
	@RequestScope
	public RequestDto getRequestDto() {
		return new RequestDto();
	}
	
	@Bean
	JwtInterceptor getJwtInterceptor() {
		return new JwtInterceptor(getRequestDto());
	}
	
		

}
