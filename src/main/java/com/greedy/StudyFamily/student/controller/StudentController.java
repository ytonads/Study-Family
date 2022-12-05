package com.greedy.StudyFamily.student.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.student.dto.StudentDto;
import com.greedy.StudyFamily.student.service.StudentService;

@RestController
@RequestMapping("/api/v1")
public class StudentController {
	
	private final StudentService studentService;
	
	public StudentController(StudentService studentService) {
		
		this.studentService = studentService;
	}
	
	/* [학생] 내 정보 조회 */
	@GetMapping("/student/mypage/{studentNo}")
	public ResponseEntity<ResponseDto> selectMyStudentNo(@PathVariable Long studentNo) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "내 정보 조회 성공", studentService.selectMyInfo(studentNo)));
	}
	
	/* [학생] 내 정보 - 개인정보 수정*/
	@PutMapping("/student/mypage/{studentNo}")
	public ResponseEntity<ResponseDto> updateStudent(@ModelAttribute StudentDto studentDto) {
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "개인 정보 수정 성공", studentService.updateStudent(studentDto)));
	}

}
