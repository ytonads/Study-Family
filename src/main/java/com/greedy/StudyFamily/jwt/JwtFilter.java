package com.greedy.StudyFamily.jwt;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class JwtFilter extends OncePerRequestFilter {
	
	private static final String AUTHORIZATION_HEADER = "Authorization";
	private static final String BEARER_PREFIX = "Bearer ";	//띄어쓰기를 해야 Bearer 그 다음부터 토큰이 불러져온다.
	
	private final TokenProvider tokenProvider;
	
	public JwtFilter(TokenProvider tokenProvider) {
		this.tokenProvider = tokenProvider;
	}
	
	

	/* 실제 필터링의 로직을 작성하는 메소드
	 * JWT 토큰의 인증 정보를 현재 스레드의 SecurityContext에 저장하는 역할을 수행 */
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		
		log.info("[JwtFilter] doFilterInternal Start ===================");
		
		// 1. Request에서 Token을 꺼낸다.
		String jwt = resolveToken(request);
		log.info("[JwtFilter] jwt : {}", jwt);
		
		// 2. validateToken으로 토큰 유효성 검사 -> 통과하면 
		// 정상 토큰일 경우 해당 토큰으로 Authentication을 가져와서 SecurityContext에 저장 -> 해줘야 인가 처리 가능
		if(StringUtils.hasText(jwt) && tokenProvider.validateToken(jwt)) {
			Authentication authentication = tokenProvider.getAuthentication(jwt);
			SecurityContextHolder.getContext().setAuthentication(authentication);
		}
		
		// 3. 다음 필터로 진행
		filterChain.doFilter(request, response);
		
		
		log.info("[JwtFilter] doFilterInternal End ===================");
		
	}

	
	//Access된 Token을 꺼내는 과정 로직	- Postman에서 조회 방법 : Authorization -> 
	private String resolveToken(HttpServletRequest request) {
		String bearerToken = request.getHeader(AUTHORIZATION_HEADER);
		// PostMan에서 BearerToken으로 접근하기 위한 설정
		if(StringUtils.hasText(bearerToken) && bearerToken.startsWith(BEARER_PREFIX)) {
			return bearerToken.substring(7);
		}
		return null;
	}


}
