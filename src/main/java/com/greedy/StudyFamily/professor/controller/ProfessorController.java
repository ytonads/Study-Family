package com.greedy.StudyFamily.professor.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.professor.service.ProfessorService;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ProfessorController {
	
	private final ProfessorService professorService;
	
	public ProfessorController(ProfessorService professorService) {
		
		this.professorService = professorService;
	}
	
	/* 태익 - [교수] 내 정보 조회 */
	@GetMapping("/professor/mypage/{professorCode}")
	public ResponseEntity<ResponseDto> selectMyProfessorCode(@PathVariable Long professorCode) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "내 정보 조회 성공", professorService.selectMyInfo(professorCode)));
	}
	
	/* 태익 - [교수] 내 정보 - 개인정보 수정 */
	@PutMapping("/professor/mypage/{professorCode}")
	public ResponseEntity<ResponseDto> updateProfessor(@ModelAttribute ProfessorDto professorDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "개인 정보 수정 성공", professorService.updateProfessor(professorDto)));
	}

}
