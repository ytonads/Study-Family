package com.greedy.StudyFamily.lecture.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.board.dto.SubnoticeDto;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.lecture.service.SubnoticeService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/subnotice")
public class SubnoticeController {

	private final SubnoticeService subnoticeService;
	
	public SubnoticeController(SubnoticeService subnoticeService) {
		this.subnoticeService = subnoticeService;	
	}
	
	//강좌공지 목록 조회
		@GetMapping("/list")
		public ResponseEntity<ResponseDto> selectSubnoticeList(@RequestParam(name = "page", defaultValue="1") int page){
			log.info("[SubnoticeController] selectAllSubnotice start =======================================");
			log.info("[SubnoticeController] page : {}", page);
			
			Page<SubnoticeDto> subnoticeDtoList = subnoticeService.selectSubnoticeList(page);
			
			PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(subnoticeDtoList);
			
			log.info("[SubnoticeController] pageInfo : {}", pageInfo);
			
			ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
			responseDtoWithPaging.setPageInfo(pageInfo);
			responseDtoWithPaging.setData(subnoticeDtoList.getContent());
			
			log.info("[SubnoticeController] selectAllSubnotice End =========================================");
			
			return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));

		}
		
}
