package com.greedy.StudyFamily.student.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.exception.UserNotFoundException;
import com.greedy.StudyFamily.student.dto.StudentDto;
import com.greedy.StudyFamily.student.entity.Student;
import com.greedy.StudyFamily.student.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;
	
	public StudentService(StudentRepository studentRepository
			            , ModelMapper modelMapper) {
		
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
	}
	
	/* [학생] 내 정보 조회 */
	public StudentDto selectMyInfo(String studentNo) {
		
		log.info("[StudentService] selectMyInfo Start ===========================");
		log.info("[StudentService] studentNo : {}", studentNo);
		
		Student student = studentRepository.findByStudentNo(studentNo)
				.orElseThrow(() -> new UserNotFoundException(studentNo + "를 찾을 수 없습니다."));
		
		log.info("[StudentService] student : {}", student);
		
		log.info("[StudentService] selectMyInfo End ===========================");
		return modelMapper.map(student, StudentDto.class);

	}
	
	/* [학생] 내 정보 조회 - 개인정보 수정 */
//	@Transactional
//	public StudentDto updateStudent(StudentDto studentDto) {
//		
//		log.info("[StudentService] updateStudent Start ===================================");
//		log.info("[StudentService] studentDto : {}", studentDto);
//		
//		Student oriStudent = studentRepository.findByStudentNo(studentDto.getStudentNo())
//				.orElseThrow(() -> new IllegalArgumentException("해당 학생이 없습니다. studentNo = " + studentDto.getStudentNo()));
//
//		oriStudent.update(studentDto.getStudentEmail()
//				        , studentDto.getStudentPhone()
//				        , studentDto.getStudentAddress());
//		
//		studentRepository.save(oriStudent);
//		
//		log.info("[StudentService] updateStudent End =====================================");
//		
//		return studentDto;
//	}

}
