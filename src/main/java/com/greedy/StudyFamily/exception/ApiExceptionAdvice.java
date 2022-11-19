package com.greedy.StudyFamily.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.greedy.StudyFamily.admin.exception.LoginFailedException;
import com.greedy.StudyFamily.exception.dto.ApiExceptionDto;

@RestControllerAdvice
public class ApiExceptionAdvice {
	
	
	//회원가입
		//DuplicatedUsernameException.class가 연결 되었을 때 해당 에러 상황에서 보여줄 메세지 핸들링
		@ExceptionHandler(DuplicatedUsernameException.class)
		public ResponseEntity<ApiExceptionDto> exceptionHandler(DuplicatedUsernameException e){
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)	//BAD_REQUEST : 400번 코드(값들이 모두 안 넘어왔을떄 발생)
					.body(new ApiExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
		}
		
		
		//로그인
		//LoginFailedException.class가 연결 되었을 때 에러 시 보여줄 메세지 핸들링
		@ExceptionHandler(LoginFailedException.class)
		public ResponseEntity<ApiExceptionDto> exceptionHandler(LoginFailedException e){
			return ResponseEntity
					.status(HttpStatus.BAD_REQUEST)
					.body(new ApiExceptionDto(HttpStatus.BAD_REQUEST, e.getMessage()));
		}
		
		//Token 발급
		// TokenException.class가 연결 되었을 때 에러 시 보여줄 메세지 핸들링
		@ExceptionHandler(TokenException.class)
		public ResponseEntity<ApiExceptionDto> exceptionHandler(TokenException e){
			return ResponseEntity
					.status(HttpStatus.UNAUTHORIZED)
					.body(new ApiExceptionDto(HttpStatus.UNAUTHORIZED, e.getMessage()));
		
		}
		
		@ExceptionHandler(RuntimeException.class)
		public ResponseEntity<ApiExceptionDto> exceptionHandler(RuntimeException e){
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(new ApiExceptionDto(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
		}
		
		
}
