package com.greedy.StudyFamily.professor.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.professor.service.ProfessorListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class ProfessorListController {
	
	private final ProfessorListService professorListService;
	
	public ProfessorListController(ProfessorListService professorListService) {
		this.professorListService = professorListService;
	}

	
	/* 교수 목록 조회 */
	@GetMapping("/professor-management")
	public ResponseEntity<ResponseDto> selectProfessorListForAdmin(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[ProfessorListController] selectProfessorListForAdmin Start =================================");
		log.info("[ProfessorListController] page : {}", page);
		
		Page<ProfessorDto> professorDtoList = professorListService.selectProfessorListForAdmin(page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(professorDtoList);
		
		log.info("[ProfessorListController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(professorDtoList.getContent());
		
		log.info("[ProfessorController] selectProfessorListForAdmin End ====================================== ");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
		
		
	/* 교수 상세 조회 */
	@GetMapping("/professorlist/{professorCode}")
	public ResponseEntity<ResponseDto> selectProfessorDetailForAdmin(@PathVariable Long professorCode){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "교수 정보 조회 성공", professorListService.selectProfessorListForAdmin(professorCode)));
	}
	
	/* 교수 정보 등록 */
	@PostMapping("/professor")
	public ResponseEntity<ResponseDto> insertProfessor(@RequestBody ProfessorDto professorDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "교수 정보 등록 완료", professorListService.insertProfessor(professorDto)));
	}
	
	
	/* 교수 정보 수정 */
	@PutMapping("/professor")
	public ResponseEntity<ResponseDto> updateProfessor(@RequestBody ProfessorDto professorDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "교수 정보 수정 완료", professorListService.updateProfessor(professorDto)));
	}
	
	
	
	
	
}
