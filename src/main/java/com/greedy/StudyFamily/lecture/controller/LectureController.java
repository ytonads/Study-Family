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

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class LectureController {
	
	private final LectureService lectureService;
	
	public LectureController(LectureService lectureService) {
		this.lectureService = lectureService;
	}
	
	//강좌 목록 조회
	@GetMapping("/lectures")
	public ResponseEntity<ResponseDto> selectLectureList(@RequestParam(name = "page", defaultValue="1") int page){
		log.info("[LectureController] selectAllLecture start =======================================");
		log.info("[LectureController] page : {}", page);
		
		Page<LectureDto> lectureDtoList = lectureService.selectLectureList(page);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(lectureDtoList);
		
		log.info("[LectureController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(lectureDtoList.getContent());
		
		log.info("[LectureController] selectAllLecture End =========================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));

	}
	
	
	
	//강좌 코드 기준으로 강의실 상세 조회(학생)
	@GetMapping("/lecture/{lectureCode}")
	public ResponseEntity<ResponseDto> selectLectureDetail(@PathVariable Long lectureCode){
		
		return ResponseEntity
				.ok()
				.body(new ResponseDto(HttpStatus.OK, "강의실 상세 조회 성공", lectureService.selectLecture(lectureCode)));
	}
	
	
	//강좌 코드 기준으로 강의실 상세 조회(교수)
//	@GetMapping("/lectures-detail-professor/{lectureCode}")
//	public ResponseEntity<ResponseDto> selectLectureDetailForProfessor(@PathVariable Long lectureCode){
//			
//		return ResponseEntity
//				.ok()
//				.body(new ResponseDto(HttpStatus.OK, "강의실 상세 조회 성공", lectureService.selectLectureForProfessor(lectureCode)));
//	}
	
	
	//수업 자료 등록(교수)
	/*@PostMapping("/files")
	public ResponseEntity<ResponseDto> insertFiles(@ModelAttribute LectureDto lectureDto){
		
		return ResponseEntity
				.ok()
				.body(new ResponseDto(HttpStatus.OK, "수업 자료 등록이 완료되었습니다.", lectureService.insertFile(lectureDto)));
	}*/

}
