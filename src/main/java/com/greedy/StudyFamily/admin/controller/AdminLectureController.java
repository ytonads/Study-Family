package com.greedy.StudyFamily.admin.controller;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.service.AdminLectureService;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.lecture.dto.LectureDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("admin/lecture")
public class AdminLectureController {

	private final AdminLectureService lectureService;
	private final ModelMapper modelMapper;
	
	
	public AdminLectureController(AdminLectureService lectureService, ModelMapper modelMapper) { 
		
		this.lectureService = lectureService;
		this.modelMapper = modelMapper;
	}
	
	/*<관리자> 강좌조회(코드기준) */
	@GetMapping("code")
	public ResponseEntity<ResponseDto> codeLectureList(@RequestParam(name="page", defaultValue="1")int page){
		
		Page<LectureDto> lectureList = lectureService.codeLectureList(page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(lectureList);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(lectureList.getContent());
				
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
}
