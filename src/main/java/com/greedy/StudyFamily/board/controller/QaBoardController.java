package com.greedy.StudyFamily.board.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.board.dto.QaBoardDto;
import com.greedy.StudyFamily.board.service.QaBoardService;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("board")
@Slf4j
public class QaBoardController {

	private final QaBoardService qaboardSerivce;
	
	
	public QaBoardController(QaBoardService qaboardSerivce) {
		this.qaboardSerivce = qaboardSerivce;
	}
	
	@GetMapping("students")
	public ResponseEntity<ResponseDto> InquiryBoardList(@RequestParam(name="page", defaultValue="1") int page){
	
		log.info("[QaBoarController] Start===============================");

		Page<QaBoardDto> qaboardList = qaboardSerivce.InquiryBoardList(page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(qaboardList);
		log.info("[QaBoardController] pageInfo : {}" + pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(qaboardList.getContent());
		
		log.info("[QaBoarController] End===============================");
		
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging)) ;
	}
	
	
	
	
	
	
	
}
