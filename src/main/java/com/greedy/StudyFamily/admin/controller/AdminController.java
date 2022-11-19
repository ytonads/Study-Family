package com.greedy.StudyFamily.admin.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.service.AdminService;
import com.greedy.StudyFamily.common.ResponseDto;

@RestController
@RequestMapping("/auth")
public class AdminController {

	private final AdminService adminService;
	
	public AdminController(AdminService adminService) {
		this.adminService = adminService;
	}
	
	/* 1. 회원 가입 */		//Postman으로 확인할 때 Body -> raw -> JSON 선택 후 확인!!
	/*@PostMapping("/signup")
	public ResponseEntity<ResponseDto> signup(@RequestBody LoginDto loginDto){
		
			//ResponseDTO IN (status , message, data)
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "회원 가입 성공", adminService.signup(loginDto)));
	
	}*/
	
	
	/* 2. 로그인 */
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> logint(@RequestBody LoginDto loginDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "로그인 성공", adminService.logint(loginDto)));
	
	}
	
	
}
