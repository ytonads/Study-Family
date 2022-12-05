package com.greedy.StudyFamily.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.board.dto.SchoolNoticeDto;
import com.greedy.StudyFamily.board.service.SchoolNoticeService;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class SchoolNoticeController {
	
	private final SchoolNoticeService schoolNoticeService;
	
	public SchoolNoticeController(SchoolNoticeService schoolNoticeService) {
		this.schoolNoticeService = schoolNoticeService;
	}

	
	/* 공지 목록 조회 */
	@GetMapping("/schoolnotice")
	public ResponseEntity<ResponseDto> selectSchoolNoticeList(@RequestParam(name="page", defaultValue="1") int page) {
		
		Page<SchoolNoticeDto> schoolNoticeDtoList = schoolNoticeService.selectSchoolNoticeList(page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(schoolNoticeDtoList);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(schoolNoticeDtoList.getContent());
				
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	
	/* 공지 목록 조회 - 검색 */
	@GetMapping("/schoolnotice/search")
	public ResponseEntity<ResponseDto> selectSearchList(@RequestParam(name="page", defaultValue="1")int page, @RequestParam(name="search") String schoolNoticeCategory){
		
		Page<SchoolNoticeDto> schoolNoticeDtoList = schoolNoticeService.selectSchoolNoticeListBySchoolNoticeCategory(page, schoolNoticeCategory);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(schoolNoticeDtoList);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(schoolNoticeDtoList.getContent());
		
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	/* 공지 상세 조회 */
	@GetMapping("/schoolnotice/{schoolNoticeCode}")
	public ResponseEntity<ResponseDto> selectSchoolNoticeDetail(@PathVariable Long schoolNoticeCode) {
		
		return ResponseEntity
				.ok().body(new ResponseDto(HttpStatus.OK, "공지 상세 정보 조회 성공", schoolNoticeService.selectSchoolNotice(schoolNoticeCode)));
	}
	
	
	
	/* 공지 등록 */
	@PostMapping("/schoolnotice")
	public ResponseEntity<ResponseDto> insertSchoolNotice(@RequestBody SchoolNoticeDto schoolNoticeDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "공지 등록 성공", schoolNoticeService.insertSchoolNotice(schoolNoticeDto)));
	}
	
	/* 공지 수정 */
	@PutMapping("/schoolnotice")
	public ResponseEntity<ResponseDto> updateSchoolNotice(@RequestBody SchoolNoticeDto schoolNoticeDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "공지 수정 완료", schoolNoticeService.updateSchoolNotice(schoolNoticeDto)));
	}
	
	
	
	
	
}
