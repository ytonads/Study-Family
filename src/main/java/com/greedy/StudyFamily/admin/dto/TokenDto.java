package com.greedy.StudyFamily.admin.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class TokenDto {

	private String grantType;	//재 요청 시 어떤 타입으로 요청해야 하는지
	private String memberName;	//기억하고 싶은 데이터가 있는경우 담기위해 일단 선언해 놓은 객체
	private String accessToken;	//최종적으로 클라이언트에게 반환되는 데이터를 담기 위한 객체
	private Long accessTokenExpiresIn;	//토큰이 언제까지 유효한지에 대한 데이터를 담기 위한 객체
	
}
