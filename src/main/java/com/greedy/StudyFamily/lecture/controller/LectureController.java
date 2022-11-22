package com.greedy.StudyFamily.lecture.controller;


import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.dto.FileDto;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.service.LectureService;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/api/v1")
public class LectureController {
	
	private final LectureService lectureService;
	
	public LectureController(LectureService lectureService) {
		this.lectureService = lectureService;
	}
	
	
	//강좌 목록 조회 - 학생
	@GetMapping("/student/{studentNo}")
	public ResponseEntity<ResponseDto> selectLectureStuList(@PathVariable Long studentNo, @RequestParam(name = "page", defaultValue="1") int page){
		
		log.info("[LectureController] selectLectureStuList Start =======================================");
		log.info("[LectureController] page : {}", page);
		
		StudentDto studentDto = new StudentDto();
		studentDto.setStudentNo(studentNo);
		
		Page<LectureDto> lectureDtoStuList = lectureService.selectLectureStuList(page, studentDto);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(lectureDtoStuList);
		
		log.info("[ProductController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(lectureDtoStuList.getContent());
		
		log.info("[LectureController] selectLectureStuList End =======================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "학생 강좌 목록 조회 성공", responseDtoWithPaging));
	}
	
	
	
	//강좌 목록 조회 - 교수
	@GetMapping("/professor/{professorCode}")
	public ResponseEntity<ResponseDto> selectLectureProList(@PathVariable Long professorCode, @RequestParam(name="page", defaultValue="1") int page){
		
		log.info("[LectureController] selectLectureProList Start =======================================");
		log.info("[LectureController] page : {}", page);
		
		
		ProfessorDto professorDto = new ProfessorDto();
		professorDto.setProfessorCode(professorCode);
		
		Page<LectureDto> lectureDtoProList = lectureService.selectLectureProList(page, professorDto);
		
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(lectureDtoProList);
		
		log.info("[ProductController] pageInfo : {}", pageInfo);
		
		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(lectureDtoProList.getContent());
		
		log.info("[LectureController] selectLectureProList End =======================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "교수 강좌 목록 조회 성공", responseDtoWithPaging));
		
	}

	
	
	
	//강좌 상세 조회 - 학생	
	@GetMapping("/student/{studentNo}/lecture/{lectureCode}")
	public ResponseEntity<ResponseDto> selectLectureDetailStu(@PathVariable Long lectureCode, @PathVariable Long studentNo){
		
		log.info("[LectureController] selectLectureDetailStu Start =======================================");
		log.info("[LectureController] lectureCode : {}", lectureCode);
		log.info("[LectureController] studentNo : {}", studentNo);
		
		LectureDto lectureDtoDeStuList = lectureService.selectLectureDetailStu(lectureCode, studentNo);
		
		log.info("[LectureController] lectureDtoDeStuList : {}", lectureDtoDeStuList);
		log.info("[LectureController] selectLectureDetailStu End =======================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "학생 강좌 상세 조회 성공", lectureDtoDeStuList));
	}	
	
	
	
	//강좌 상세 조회 - 교수
	@GetMapping("professor/{professorCode}/lecture/{lectureCode}")
	public ResponseEntity<ResponseDto> selectLectureDetailPro(@PathVariable Long professorCode, @PathVariable Long lectureCode){
		
		log.info("[LectureController] selectLectureDetailPro Start =======================================");
		log.info("[LectureController] lectureCode : {}", lectureCode);
		log.info("[LectureController] professorCode : {}", professorCode);
		
		LectureDto lectureDtoDeProList = lectureService.selectLectureDetailPro(lectureCode, professorCode);
		
		log.info("[LectureController] lectureDtoDeProList : {}", lectureDtoDeProList);
		log.info("[LectureController] selectLectureDetailPro End =======================================");
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "교수 강좌 상세 조회 성공", lectureDtoDeProList));
	}
	
	
	//수업 자료 등록 - 교수
	@PostMapping("/lectures")
	public ResponseEntity<ResponseDto> insertLectureFile(@ModelAttribute FileDto fileDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "파일 등록 성공", lectureService.insertLectureFile(fileDto)));
	}
	
	
	
	//수업 자료 수정 - 교수
	@PutMapping("/lectures")
	public ResponseEntity<ResponseDto> updateLectureFile(@ModelAttribute FileDto fileDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "파일 수정 성공", lectureService.updateLectureFile(fileDto)));
	}
	
	
	
	//과제 파일 등록 - 학생
	@PostMapping("/tasks")
	public ResponseEntity<ResponseDto> insertTaskFile(@ModelAttribute FileDto fileDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "과제 등록 성공", lectureService.insertTaskFile(fileDto)));
	}
	
	
	//과제 파일 수정 - 학생
	@PutMapping("/tasks")
	public ResponseEntity<ResponseDto> updateTaskFile(@ModelAttribute FileDto fileDto){
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "과제 수정 성공", lectureService.updateTaskFile(fileDto)));
	}
	
	
}
