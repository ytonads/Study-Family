package com.greedy.StudyFamily.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.service.AdminService;
import com.greedy.StudyFamily.common.ResponseDto;

@RestController	// 반환값이 응답데이터 되도록 일괄처리 하기 위해 사용
@RequestMapping("/auth")
public class AdminController {

	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}

	
	
	/* 1. 관리자 회원 가입(관리자 로그인 가데이터 넣기위해서 관리자도 가입은 가능해야 함) */
	@PostMapping("/regist")
	public ResponseEntity<ResponseDto> regist(@RequestBody LoginDto loginDto) {
		
		// responsedto에 status, message, data 전송
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "회원 가입이 완료되었습니다.", adminService.regist(loginDto)));
		
	}
	
	/* 2. 로그인 */
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> login(@RequestBody LoginDto loginDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "로그인 성공", adminService.login(loginDto)));
		
	}
	
	
}
