package com.greedy.StudyFamily.board.controller;

import java.util.List;

import org.springframework.data.domain.Page;
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
import com.greedy.StudyFamily.board.dto.SubNoticeDto;
import com.greedy.StudyFamily.board.service.SubnoticeService;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.lecture.dto.AppClassDto;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.Lecture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/board")
public class SubnoticeController {

	private final SubnoticeService subnoticeService;
	
	public SubnoticeController(SubnoticeService subnoticeService) {
		this.subnoticeService = subnoticeService;	
	}
	
	//강좌공지 목록 조회
	@GetMapping("/subnotices")
	public ResponseEntity<ResponseDto> selectSubnoticeList(@RequestParam(name = "page", defaultValue="1") int page){
		
		log.info("[SubnoticeController] selectSubnoticeList start =======================================");
		log.info("[SubnoticeController] page : {}", page);
			
		Page<SubNoticeDto> subnoticeDtoList = subnoticeService.selectSubnoticeList(page);
			
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(subnoticeDtoList);
			
		log.info("[SubnoticeController] pageInfo : {}", pageInfo);
			
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(subnoticeDtoList.getContent());
			
		log.info("[SubnoticeController] selectAllSubnotice End =========================================");
			
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));

	}
		
	
	//강좌공지 상세조회
	@GetMapping("/subnotices/{subnoticeCode}")
	public ResponseEntity<ResponseDto> selectSubnoticeDetail(@PathVariable Long subnoticeCode) {
		
		return ResponseEntity
				.ok()
				.body(new ResponseDto(HttpStatus.OK, "강좌공지 상세 조회 성공", subnoticeService.selectSubNotice(subnoticeCode)));
		
	}
	
	
	
	  //강좌공지 작성
	  @PostMapping("/subnotices/make") 
	  public ResponseEntity<ResponseDto> insertSubnotice(@RequestBody SubNoticeDto subnoticeDto, 
			  @AuthenticationPrincipal LoginDto loginUser) {

	  log.info("subnoticeDto : {}", subnoticeDto);
		  
	  return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "강좌공지 작성 성공",
	  subnoticeService.insertSubnotice(subnoticeDto, loginUser))); 
	  }
	 
	 
	  //강좌공지 수정
		@PutMapping("/subnotices/make")
		public ResponseEntity<ResponseDto> updateSubnotice(@RequestBody SubNoticeDto subNoticeDto) 
		{
			return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "강좌공지 수정 성공", 
					subnoticeService.updateSubnotice(subNoticeDto)));	
		}
	  
		//강좌공지 삭제
		@DeleteMapping("/subnotices/delete/{subnoticeCode}")
		public ResponseEntity<?> removeSubnotice(@ModelAttribute SubNoticeDto subnoticeDto,
				 @PathVariable("subnoticeCode") Long subnoticeCode) {
			
			log.info("subnoticeDto : {}", subnoticeDto);
			
			subnoticeService.deleteSubnotice(subnoticeCode);
			
			return ResponseEntity 
					.noContent() 
					.build();
		}
		

				
}
