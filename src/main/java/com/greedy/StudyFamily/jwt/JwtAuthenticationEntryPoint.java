package com.greedy.StudyFamily.jwt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.greedy.StudyFamily.exception.dto.ApiExceptionDto;

@Component				//인증에 실패 했을 때 다뤄줄 핸들러~~!
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {
	
	private final ObjectMapper objectMapper;
	
	public JwtAuthenticationEntryPoint(ObjectMapper objectMapper) {
		this.objectMapper = objectMapper;
	}

	
	@Override
	// SecurityConfig가 잘 호출되지 않으면 commence가 호출된다.
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		// 유효한 자격 증명을 제공하지 않고 접근하려 할 때 401 오류
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		// DispatcherServlet의 Controller를 쓴다면 ResponseEntity를 사용하겠지만 우리는 Filter를 사용하여 바로 반환할 예정. 고로 그때 필요한 예외처리를 위해 ApiExceptionDto를 사용한다.
		ApiExceptionDto errorResponse = new ApiExceptionDto(HttpStatus.UNAUTHORIZED, "인증 되지 않은 유저입니다.");
		// Java Object -> JSON String 변환 => objectMapper.writeValueAsString(object)
		response.getWriter().write(objectMapper.writeValueAsString(errorResponse));
				
	}
	

}
