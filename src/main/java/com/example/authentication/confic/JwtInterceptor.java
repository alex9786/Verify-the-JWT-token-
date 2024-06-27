package com.example.authentication.confic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.authentication.common.JwtToken;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	
	@Autowired
	JwtToken jwtToken;
	

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		String autho = request.getHeader("authorization");

		if (!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signUp"))) {
			jwtToken.verify(autho);
		}

		return true;
	}
	
}
