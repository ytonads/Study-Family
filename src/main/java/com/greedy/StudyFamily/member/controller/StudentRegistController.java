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
import com.greedy.StudyFamily.member.Service.StudentRegistService;

@RestController
@RequestMapping("/studentauth")
public class StudentRegistController {

	private final StudentRegistService studentRegistService;
	private final AdminService adminService;
	
	public StudentRegistController(StudentRegistService studentRegistService, AdminService adminService) {
		this.studentRegistService = studentRegistService;
		this.adminService = adminService;
	}
	
//	/* 1. 학생 전용 회원가입 */
//	@PostMapping("/studentRegist")
//	public ResponseEntity<ResponseDto> professorRegist(@RequestBody ProfessorRegistDto professorRegistDto, @RequestBody StudentRegistDto studentRegistDto) {
//		
//		// responsedto에 status, message, data 전송
//		// studentRegistDto 안쓰면 ProfessorService에서 학생 아이디와 중복 확인을 못 함
//		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "학생의 가입이 완료되었습니다.", professorRegistService.professorRegist(professorRegistDto, studentRegistDto)));
//		
//	}
	
	/* 1. 학생 회원 가입 */
	@PostMapping("/studentRegist")
	public ResponseEntity<ResponseDto> regist(@RequestBody LoginDto loginDto) {
		
		// responsedto에 status, message, data 전송
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "학생의 가입이 완료되었습니다.", studentRegistService.studentRegist(loginDto)));
		//return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "회원 가입이 완료되었습니다.", adminService.regist(loginDto)));
		
	}
	
	/* 2. 학생 전용 로그인 */
	@PostMapping("/studentLogin")
	public ResponseEntity<ResponseDto> login(@RequestBody LoginDto loginDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "학생 로그인 성공", studentRegistService.login(loginDto)));
		
	}
	
}
