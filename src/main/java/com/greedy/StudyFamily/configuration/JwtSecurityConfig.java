package com.greedy.StudyFamily.configuration;

import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.greedy.StudyFamily.jwt.JwtFilter;
import com.greedy.StudyFamily.jwt.TokenProvider;


public class JwtSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

	private final TokenProvider tokenProvider;
	
	public JwtSecurityConfig(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	@Override
	public void configure(HttpSecurity http) {
		JwtFilter customFilter = new JwtFilter(tokenProvider);
		/* 인증을 처리하는 기본 필터 UsernamePasswordAuthenticationFilter 대신 별도의 인증 로직을 가진 필터를 생성하고 사용하고 싶을 때
		 * 해당 필터 앞에 커스텀 필터를 넣는다. */
		// 개발자가 원하는 customFilter를 UsernamePasswordAuthenticationFilter 앞에 설정하여 필터링 처리 하기위해 작성 
		http.addFilterBefore(customFilter, UsernamePasswordAuthenticationFilter.class);
	}
	
}
