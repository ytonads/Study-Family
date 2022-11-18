package com.greedy.StudyFamily.lecture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.service.LectureService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/lecture")
public class LectureController {
	
//	private final LectureService lectureService;
	
//	public LectureController(LectureService lectureService) {
//		this.lectureService = lectureService;
//	}
//	
//	
//	//강좌 코드 기준으로 강의실 상세 조회(학생)
//	@GetMapping("/lecture-detail/{lectureCode}")
//	public ResponseEntity<ResponseDto> selectLectureDetail(@PathVariable Long lectureCode){
//		
//		return ResponseEntity
//				.ok()
//				.body(new ResponseDto(HttpStatus.OK, "강의실 상세 조회 성공", lectureService.selectLecture(lectureCode)));
//	}
//	
//	
//	//강좌 코드 기준으로 강의실 상세 조회(교수)
//	@GetMapping("/lecture-detail/{lectureCode}")
//	public ResponseEntity<ResponseDto> selectLectureDetailForProfessor(@PathVariable Long lectureCode){
//			
//		return ResponseEntity
//				.ok()
//				.body(new ResponseDto(HttpStatus.OK, "강의실 상세 조회 성공", lectureService.selectLectureForProfessor(lectureCode)));
//	}
//	
//	
//	//수업 자료 등록(교수)
//	@PostMapping("/files")
//	public ResponseEntity<ResponseDto> insertFiles(@ModelAttribute LectureDto lectureDto){
//		
//		return ResponseEntity
//				.ok()
//				.body(new ResponseDto(HttpStatus.OK, "수업 자료 등록이 완료되었습니다.", lectureService.insertFile(lectureDto)));
//	}

}
