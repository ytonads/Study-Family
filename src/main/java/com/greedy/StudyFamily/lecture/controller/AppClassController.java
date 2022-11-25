package com.greedy.StudyFamily.lecture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
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
	@PostMapping("/appClass/go")
	public ResponseEntity<ResponseDto> insertAppClass(@RequestBody AppClassDto appClassDto,
			@AuthenticationPrincipal LoginDto loginUser) {

		appClassDto.setStudent(loginUser.getStudent());

		log.info("appClassDto : {}", appClassDto);

		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "수강신청 완료", appClassService.insertAppClass(appClassDto)));

	}

	//수강취소
	 @DeleteMapping("/appClass/delete/{appClassCode}") 
	 public ResponseEntity<ResponseDto> deleteAppClass(@ModelAttribute AppClassDto appClassDto,
			 @PathVariable("appClassCode") Long appClassCode,
			 @AuthenticationPrincipal LoginDto loginUser) {
		 
		appClassDto.setStudent(loginUser.getStudent());

		log.info("appClassDto : {}", appClassDto);
			
		appClassService.deleteAppClass(appClassCode);
		
	 
	 return ResponseEntity
			 .noContent()
			 .build();
	 }
	
	  
	 

	/* 수강신청한 리스트 목록 */
	  @GetMapping("/appClass/{studentNo}") 
	  public ResponseEntity<ResponseDto> getAppClass(@PathVariable Long studentNo) {
	  
	  return ResponseEntity 
			  .ok() 
			  .body(new ResponseDto(HttpStatus.OK, "수강 신청한 리스트 조회 완료", appClassService.selectAppClassList(studentNo))); 
	  }
	 
}
