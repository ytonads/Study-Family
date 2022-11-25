package com.greedy.StudyFamily.member.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.member.Service.ProfessorRegistService;
import com.greedy.StudyFamily.member.dto.ProfessorRegistDto;
import com.greedy.StudyFamily.member.dto.StudentRegistDto;

@RestController
@RequestMapping("/professorauth")
public class ProfessorRegistController {

	private final ProfessorRegistService professorRegistService;
	
	public ProfessorRegistController(ProfessorRegistService professorRegistService) {
		this.professorRegistService = professorRegistService;
	}
	
	/* 1. 교수 전용 회원가입 */
	@PostMapping("/professorRegist")
	public ResponseEntity<ResponseDto> professorRegist(@RequestBody ProfessorRegistDto professorRegistDto, @RequestBody StudentRegistDto studentRegistDto) {
		
		// responsedto에 status, message, data 전송
		// studentRegistDto 안쓰면 ProfessorService에서 학생 아이디와 중복 확인을 못 함
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.CREATED, "교수님의 가입이 완료되었습니다.", professorRegistService.professorRegist(professorRegistDto, studentRegistDto)));
		
	}
	
	/* 2. 교수 전용 로그인 */
	@PostMapping("/professorLogin")
	public ResponseEntity<ResponseDto> professorLogin(@RequestBody ProfessorRegistDto professorRegistDto, @RequestBody StudentRegistDto studentRegistDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "교수님 로그인 성공", professorRegistService.login(professorRegistDto)));
		
	}	
	
}
