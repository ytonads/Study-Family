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
import com.greedy.StudyFamily.member.Service.ProfessorRegistService;
import com.greedy.StudyFamily.member.dto.ProfessorRegistDto;
import com.greedy.StudyFamily.member.dto.StudentRegistDto;

@RestController
@RequestMapping("/professorauth")
public class ProfessorRegistController {

	private final ProfessorRegistService professorRegistService;
	private final AdminService adminService;
	
	public ProfessorRegistController(ProfessorRegistService professorRegistService, AdminService adminService) {
		this.professorRegistService = professorRegistService;
		this.adminService = adminService;
	}
	
	/* 1. 가입 */
//	@PostMapping("/professorRegist")
//	public ResponseEntity<ResponseDto> professorRegist(@RequestBody ProfessorRegistDto professorRegistDto) {
//		
//		// responsedto에 status, message, data 전송
//		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "교수님의 가입이 완료되었습니다.", professorRegistService.professorRegist(professorRegistDto)));
//		
//	}
	
	@PostMapping("/professorRegist")
	public ResponseEntity<ResponseDto> regist(@RequestBody LoginDto loginDto) {
		
		// responsedto에 status, message, data 전송
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "회원 가입이 완료되었습니다.", professorRegistService.professorRegist(loginDto)));
		
	}
	
	/* 2. 교수 전용 로그인 */
	@PostMapping("/professorLogin")
	public ResponseEntity<ResponseDto> login(@RequestBody LoginDto loginDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "로그인 성공", adminService.login(loginDto)));
		
	}
	
}
