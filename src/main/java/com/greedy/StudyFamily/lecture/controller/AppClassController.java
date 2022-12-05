package com.greedy.StudyFamily.lecture.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.lecture.dto.AppClassDto;
import com.greedy.StudyFamily.lecture.dto.EvalDto;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.Lecture;
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
	public ResponseEntity<ResponseDto> insertAppClass(@RequestBody AppClassDto appClassDto,
			@AuthenticationPrincipal LoginDto loginUser) {

		appClassDto.setStudent(loginUser.getStudent());

		log.info("appClassDto : {}", appClassDto);

		return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "수강신청 완료", appClassService.insertAppClass(appClassDto)));

	}

	//수강취소
	 @DeleteMapping("/appClass/delete/{lectureCode}") 
	 public ResponseEntity<ResponseDto> deleteAppClass(@ModelAttribute AppClassDto appClassDto,
			 @PathVariable("lectureCode") Lecture lectureCode,
			 @AuthenticationPrincipal LoginDto loginUser) {
		 
		appClassDto.setStudent(loginUser.getStudent());

		log.info("appClassDto : {}", appClassDto);
			
		appClassService.deleteAppClass(lectureCode);
		
	 
	 return ResponseEntity.ok()
				.body(new ResponseDto(HttpStatus.OK, "수강취소 완료", null));
	 }
	
	  
	 

	/* 수강신청한 리스트 목록 */
	  @GetMapping("/appClass/{studentNo}") 
	  public ResponseEntity<ResponseDto> getAppClass(@PathVariable Long studentNo) {
	  
	  return ResponseEntity 
			  .ok() 
			  .body(new ResponseDto(HttpStatus.OK, "수강 신청한 리스트 조회 완료", appClassService.selectAppClassList(studentNo))); 
	  }
	  
	  /* 태익 - [교수] 해당 강좌 학생 리스트 조회 */
		@GetMapping("/professor/studentlist/{lectureCode}")
		public ResponseEntity<ResponseDto> selectLectureStudentList(@PathVariable Long lectureCode, 
				@RequestParam(name = "page", defaultValue="1") int page){
				
			log.info("[AppClassController] selectLectureStudentList start =======================================");
			log.info("[AppClassController] page : {}", page);
			log.info("[AppClassController] lectureCode : {}", lectureCode);
				
			LectureDto lectureDto = new LectureDto();
			lectureDto.setLectureCode(lectureCode);
				
			List<AppClassDto> appClassDtoList = appClassService.selectStudentListByLecture(page, lectureDto);
				
			log.info("[AppClassController] selectLectureStudentList End =======================================");
			return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "동일 강좌 학생 리스트 조회 성공", appClassDtoList));
		}
	 
}
