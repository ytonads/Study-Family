package com.greedy.StudyFamily.student.controller;

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
import com.greedy.StudyFamily.student.dto.StudentDto;
import com.greedy.StudyFamily.student.service.StudentListService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class StudentListController {
	
	private final StudentListService studentListService;
	
	public StudentListController(StudentListService studentListService) {
		this.studentListService = studentListService;
	}

	/* 학생 목록 조회 */
	@GetMapping("/student-management")
	public ResponseEntity<ResponseDto> selectStudentListForAdmin(@RequestParam(name="page", defaultValue="1") int page) {
		
		log.info("[StudentListController] selectStudentListForAdmin Start =================================");
		log.info("[StudentListController] page : {}", page);
		
		Page<StudentDto> studentDtoList = studentListService.selectStudentListForAdmin(page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(studentDtoList);
		
		log.info("[StudentListController] pageInfo : {}", pageInfo);

		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(studentDtoList.getContent());
		
		log.info("[StudentListController] selectStudentListForAdemin End ================================== ");
				
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	/* 학생 상세 조회 */
	@GetMapping("/studentlist/{studentNo}")
	public ResponseEntity<ResponseDto> selectStudentDetailForAdmin(@PathVariable Long studentNo) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "학생 정보 조회 성공", studentListService.selectStudentListForAdmin(studentNo)));
	}
	
	/* 학생 정보 등록 */
	@PostMapping("/student")
	public ResponseEntity<ResponseDto> insertStudent(@RequestBody StudentDto studentDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "학생 정보 등록 완료", studentListService.insertStudent(studentDto)));
	}
	
	/* 학생 정보 수정 */
	@PutMapping("/student")
	public ResponseEntity<ResponseDto> updateStudent(@RequestBody StudentDto studentDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "학생 정보 수정 완료", studentListService.updateStudent(studentDto)));
	}
	
	
	
	
}
