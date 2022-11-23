package com.greedy.StudyFamily.exception;

// Exeption과 달리 RuntimeException 사용시 Service에서 메서드 뒤에 throws를 명시할 필요 없음 
public class LoginFailedException extends RuntimeException {

	// LoginFailedException를 생성할 때 msg라는걸 전달 받아서 상위 전달 msg로 전달하여 그걸 오류 메세지로 설정
	public LoginFailedException(String msg) {
		super(msg);
	}
}
