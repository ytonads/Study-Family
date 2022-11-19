package com.greedy.StudyFamily.lecture.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.service.LectureService;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class LectureController {
	
	private final LectureService lectureService;
	
	public LectureController(LectureService lectureService) {
		this.lectureService = lectureService;
	}
	
	
	//강좌 목록 조회 - 교수
	@GetMapping("/lectures/{professorCode}")
	public ResponseEntity<ResponseDto> selectLectureProList(@PathVariable Long professorCode, @RequestParam(name="page", defaultValue="1") int page){
		
		ProfessorDto professorDto = new ProfessorDto();
		professorDto.setProfessorCode(professorCode);
		
		
		Page<LectureDto> lectureDtoProList = lectureService.selectLectureProList(page, professorDto);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(lectureDtoProList);
		
		log.info("[ProductController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(lectureDtoProList.getContent());
		
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "교수 강좌 목록 조회 성공", responseDtoWithPaging));
		
	}
	
	
	
	
	
	
}
