package com.greedy.StudyFamily.lecture.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.dto.FileDto;
import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.lecture.dto.CourseHistoryDto;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.service.LectureService;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class LectureController {
	
	private final LectureService lectureService;
	
	public LectureController(LectureService lectureService) {
		this.lectureService = lectureService;
	}
	
	
	
	/* 강좌 목록 조회(학생)  - 완료!!! */
	@GetMapping("/student/stuLectureList")
	public ResponseEntity<ResponseDto> selectLectureStuList(@AuthenticationPrincipal LoginDto loginStu){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "학생 강좌 목록 조회 성공", lectureService.selectLectureStuList(loginStu)));
	}
	

	/* 강좌 목록 조회(교수) - 완료!!! */
	@GetMapping("/professor/proLectureList")
	public ResponseEntity<ResponseDto> selectLectureProList(@AuthenticationPrincipal LoginDto loginPro){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "교수 강좌 목록 조회 성공", lectureService.selectLectureProList(loginPro)));
	}
	
	
	/* 강좌 상세 조회 - (학생) - 완료!!! */
	@GetMapping("/student/stuLectureList/{lectureCode}")
	public ResponseEntity<ResponseDto> selectLectureDetailStu(@PathVariable Long lectureCode){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "학생 강좌 상세 조회 성공", lectureService.selectLectureDetailStu(lectureCode)));
	}


	/* 강좌 상세 조회 - (교수) - 완료!!! */
	@GetMapping("professor/proLectureList/{lectureCode}")
	public ResponseEntity<ResponseDto> selectLectureDetailPro(@PathVariable Long lectureCode){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "교수 강좌 상세 조회 성공", lectureService.selectLectureDetailPro(lectureCode)));
	}

	
	
	
	/* 수업 자료 등록(교수) - 완료!!! */
	@PostMapping("/lectures")
	public ResponseEntity<ResponseDto> insertLectureFile(@ModelAttribute FileDto fileDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "파일 등록 성공", lectureService.insertLectureFile(fileDto)));
	}
	
	
	
	
	//수강신청 강좌 목록 조회 
	@GetMapping("/appClass/list")
	public ResponseEntity<ResponseDto> selectLectureList(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[LectureController] selectAppClassList Start ================================");
		log.info("[LectureController] page : {}", page);
		
		Page<LectureDto> lectureDtoList = lectureService.selectLectureList(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(lectureDtoList);
		 
		log.info("[LectureController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(lectureDtoList.getContent());
		
		log.info("[LectureController] selectAppClassList End ================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	
	

	/* 수업 자료 수정(교수) - 완료!!! */
	@PutMapping("/lectures")
	public ResponseEntity<ResponseDto> updateLectureFile(@ModelAttribute FileDto fileDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "파일 수정 성공", lectureService.updateLectureFile(fileDto)));
	}
	
	
	
	/* 과제 파일 등록(학생) - 완료!!! */
	@PostMapping("/tasks")
	public ResponseEntity<ResponseDto> insertTaskFile(@ModelAttribute FileDto fileDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "과제 등록 성공", lectureService.insertTaskFile(fileDto)));
	}
	
	
	/* 과제 파일 수정(학생) - 완료!!! */
	@PutMapping("/tasks")
	public ResponseEntity<ResponseDto> updateTaskFile(@ModelAttribute FileDto fileDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "과제 수정 성공", lectureService.updateTaskFile(fileDto)));
	}
	
	
	/* 출결 상태 등록(학생) */
	@PostMapping("/courseHistory")
	public ResponseEntity<ResponseDto> courseHisotry(@ModelAttribute CourseHistoryDto courseHistoryDto, @AuthenticationPrincipal LoginDto student){
		
		courseHistoryDto.setStudent(student.getStudent());
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "출결 상태 등록 성공", lectureService.courseHisotry(courseHistoryDto)));
	}
	
	/* 출결 상태 수정(학생) */
	@PutMapping("/courseHistory")
	public ResponseEntity<ResponseDto> courseHisotryUpdate(@ModelAttribute CourseHistoryDto courseHistoryDto, @AuthenticationPrincipal LoginDto student){
		
		courseHistoryDto.setStudent(student.getStudent());
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "출결 상태 변경 성공", lectureService.courseHisotryUpdate(courseHistoryDto)));
	}
	
	
}
