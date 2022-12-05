package com.greedy.StudyFamily.msg.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.repository.LoginRepository;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.lecture.dto.AppClassDto;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.msg.dto.MsgDto;
import com.greedy.StudyFamily.msg.service.MsgService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class MsgController {
	
	private final MsgService msgService;
	private final LoginRepository loginRepository;
	
	public MsgController(MsgService msgService, LoginRepository loginRepository) {
		this.msgService = msgService;
		this.loginRepository = loginRepository;
	}
	
	/* 수강생 리스트 조회 - 완료!!! */
	@GetMapping("/message/lectures/{lectureCode}")
	public ResponseEntity<ResponseDto> selectStudentList(@PathVariable Long lectureCode, 
			@RequestParam(name = "page", defaultValue="1") int page){
		
		log.info("[MsgController] selectStudentList start =======================================");
		log.info("[MsgController] page : {}", page);
		log.info("[MsgController] lectureCode : {}", lectureCode);
		
		LectureDto lectureDto = new LectureDto();
		lectureDto.setLectureCode(lectureCode);
		
		Page<AppClassDto> appClassDtoList = msgService.selectStudentListByLectureCode(page, lectureDto);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(appClassDtoList);
		
		log.info("[MsgController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(appClassDtoList.getContent());
		
		log.info("[MsgController] selectStudentList End =======================================");
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "동일 강좌 학생 리스트 조회 성공", responseDtoWithPaging));
	}
	
	

	/* 쪽지 발송 - 완료!!! */
	@PostMapping("/message/send")
	public ResponseEntity<ResponseDto> sendMessage(@RequestBody MsgDto msgDto,
			@AuthenticationPrincipal LoginDto sender){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "쪽지 발송 성공", msgService.write(msgDto, sender)));
	}
	
	
	
	//쪽지 수신함 조회
	@GetMapping("/message/receive")
	public ResponseEntity<ResponseDto> getReceivedMessage(@AuthenticationPrincipal LoginDto receiver, 
			@RequestParam(name = "page", defaultValue = "1") int page){
		
		log.info("[MsgController] getReceivedMessage start =======================================");
		log.info("[MsgController] page : {}", page);
		
		Page<MsgDto> msgDtoList = msgService.selectReceivedBox(page, receiver);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(msgDtoList);
		
		log.info("[MsgController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(msgDtoList.getContent());
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "쪽지 수신함 조회 성공", responseDtoWithPaging));
	}
	
	
	//쪽지 발신함 조회
	@GetMapping("/message/send")
	public ResponseEntity<ResponseDto> getSendeddMessage(@AuthenticationPrincipal LoginDto sender, 
			@RequestParam(name = "page", defaultValue = "1") int page){
		
		log.info("[MsgController] getSendeddMessage start =======================================");
		log.info("[MsgController] page : {}", page);
		
		Page<MsgDto> msgDtoList = msgService.selectSendedBox(page, sender);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(msgDtoList);
		
		log.info("[MsgController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(msgDtoList.getContent());
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "쪽지 발신함 조회 성공", responseDtoWithPaging));
	}
	
	
	
	
}
