package com.greedy.StudyFamily.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.service.MemberService;
import com.greedy.StudyFamily.common.ResponseDto;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

	private final MemberService memberService;
	
	public AuthController(MemberService memberService) {
		this.memberService = memberService;
	}
	
	// 인증 및 인가 기능
	@GetMapping("/loginId/{loginId}")
	public ResponseEntity<ResponseDto> selectloginIdInfo(@PathVariable String loginId) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", memberService.selectMyInfo(loginId)));
		
	}
	
}
