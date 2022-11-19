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

	
	
	/* 2. 로그인 */
	@PostMapping("/login")
	public ResponseEntity<ResponseDto> logint(@RequestBody LoginDto loginDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "로그인 성공", adminService.logint(loginDto)));
	
	}
	
	
}
