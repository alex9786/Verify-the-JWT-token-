package com.example.authentication.confic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.authentication.Dto.RequestDto;
import com.example.authentication.common.JwtToken;

import io.jsonwebtoken.Claims;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtInterceptor implements HandlerInterceptor{
	
	@Autowired
	JwtToken jwtToken;
	
	private RequestDto requestDto;
	
	public JwtInterceptor(RequestDto requestDto) {
		this.requestDto=requestDto;
	}
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)throws Exception {
		String autho = request.getHeader("authorization");

		if (!(request.getRequestURI().contains("login") || request.getRequestURI().contains("signUp"))) {
			Claims claims=  jwtToken.verify(autho);
			requestDto.setId(Long.valueOf(claims.getIssuer()));
			requestDto.setName(claims.get("name").toString());
			requestDto.setEmailId(claims.get("emailid").toString());
			requestDto.setContact(claims.get("contact").toString());
			
			
		}
		return true;
	}
	
}
