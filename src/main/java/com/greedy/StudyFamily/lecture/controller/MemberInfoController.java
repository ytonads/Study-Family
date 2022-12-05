package com.greedy.StudyFamily.lecture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.common.ResponseDto;

@RestController
@RequestMapping("/info")
public class MemberInfoController {

	
	@GetMapping("/member/memeInfo")
	public ResponseEntity<ResponseDto> selectMyInfo(@AuthenticationPrincipal LoginDto myInfo){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "본인 정보 조회 성공", myInfo));
	}
}
