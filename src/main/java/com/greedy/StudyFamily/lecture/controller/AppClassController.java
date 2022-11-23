package com.greedy.StudyFamily.lecture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.lecture.dto.AppClassDto;
import com.greedy.StudyFamily.lecture.service.AppClassService;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
@RequestMapping("/api/v1")
public class AppClassController {

	private final AppClassService appClassService;
	
	public AppClassController(AppClassService appClassService) {
		this.appClassService = appClassService;
	}
	
	/* 수강신청 */
	@PostMapping("/appClass")
	public ResponseEntity<ResponseDto> insertAppClass(@RequestBody AppClassDto appClassDto , 
			@AuthenticationPrincipal LoginDto loginUser){
		
		//나중에 삭제할 코드
		
		  //StudentDto student = new StudentDto(); student.setStudentNo(3L);
		 
		
		appClassDto.setStudent(loginUser.getStudent());
		//appClassDto.setStudent(student);
		
		log.info("appClassDto : {}", appClassDto);
		
		return ResponseEntity
				.ok()
				.body(new ResponseDto(HttpStatus.OK, "수강신청 완료", appClassService.insertAppClass(appClassDto)));
		
	}
	
	
}
