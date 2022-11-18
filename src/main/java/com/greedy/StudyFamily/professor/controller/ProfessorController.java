package com.greedy.StudyFamily.professor.controller;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.professor.service.ProfessorService;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api")
public class ProfessorController {
	
	private final ProfessorService professorService;
	
	public ProfessorController(ProfessorService professorService) {
		
		this.professorService = professorService;
	}
	
	/* [교수] 내 정보 조회 */
	@GetMapping("/professor/mypage/{professorCode}")
	public ResponseEntity<ResponseDto> selectMyProfessorCode(@PathVariable String professorCode) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "내 정보 조회 성공", professorService.selectMyInfo(professorCode)));
	}
	
	/* [교수] 내 정보 - 개인정보 수정 */
	@PutMapping("/professors")
	public ResponseEntity<ResponseDto> updateProfessor(@ModelAttribute ProfessorDto professorDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "개인 정보 수정 성공", professorService.updateProfessor(professorDto)));
	}
	
	/* [교수] 해당 과목 학생 전체 조회 */
	@GetMapping("/professors/studentList")
	public ResponseEntity<ResponseDto> selectStudentList(@RequestParam(name="page", defaultValue="1") int page, @RequestParam(name="search") String studentNo) {
		
		log.info("[ProfessorController] selectStudentList Start ================================");
		log.info("[ProfessorController] page : {}", page);
		log.info("[ProfessorController] studentNo : {}", studentNo);

		Page<StudentDto> studentDtoList = professorService.selectStudentListByStudentNo(page, subCode);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(studentDtoList);
		
		log.info("[ProfessorController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(studentDtoList.getContent());
		
		log.info("[ProfessorController] selectStudentList End ==================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "해당 과목 학생 조회 성공", responseDtoWithPaging));
	}

}
