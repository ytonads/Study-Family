package com.greedy.StudyFamily.student.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.student.dto.StudentDto;
import com.greedy.StudyFamily.student.entity.SchoolStatus;
import com.greedy.StudyFamily.student.entity.Student;
import com.greedy.StudyFamily.student.repository.StudentListRepository;
import com.greedy.StudyFamily.subject.entity.Department;

import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentListService {
	
	private final StudentListRepository studentListRepository;
	private final ModelMapper modelMapper;
	
	public StudentListService(StudentListRepository studentListRepository, ModelMapper modelMapper) {
		this.studentListRepository = studentListRepository;
		this.modelMapper = modelMapper;
	}

	/* 학생 목록 조회 */
	public Page<StudentDto> selectStudentListForAdmin(int page) {
		
		log.info("[StudentListService] selectStudentListForAdmin Start ============================= ");
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("StudentNo").descending());
		
		Page<Student> studentList = studentListRepository.findAll(pageable);
		Page<StudentDto> studentDtoList = studentList.map(student -> modelMapper.map(student, StudentDto.class));
		
		log.info("[StudentListService] studentDtoList : {}", studentDtoList.getContent());
		log.info("[StudentListService] selectStudentListForAdmin End ============================= ");
		
		return studentDtoList;
	}

	/* 학생 상세 조회 */
	public StudentDto selectStudentListForAdmin(Long studentNo) {
		
		log.info("[StudentListService] selectStudentForAdmin Start ================================ ");
		log.info("[StudentListService] studentNo : " + studentNo);
		
		Student student = studentListRepository.findById(studentNo)
				.orElseThrow(() -> new IllegalArgumentException("해당 학생이 없습니다. studentNo=" + studentNo));
		StudentDto studentDto = modelMapper.map(student, StudentDto.class);
		
		log.info("[StudentListService] studentDto : " + studentDto);
		log.info("[StudentListService] selectStudentForAdmin End ================================ ");
		
		return studentDto;
	}

	/* 학생 정보 등록 */
	@Transactional
	public StudentDto insertStudent(StudentDto studentDto) {
		
		log.info("[StudentListService] insertStudent Start ======================================= ");
		log.info("[StudentListService] studentDto : {}", studentDto);

		studentListRepository.save(modelMapper.map(studentDto, Student.class));
		
		log.info("[StudentListService] insertStudent End ======================================= ");
		
		return studentDto;
	}
	
	/* 학생 정보 수정 */
	@Transactional
	public StudentDto updateStudent(StudentDto studentDto) {
		
		Student foundStudent = studentListRepository.findById(studentDto.getStudentNo())
				.orElseThrow(() -> new RuntimeException ("존재하지 않는 학생 입니다."));
		
		foundStudent.update(
				studentDto.getStudentCode(),
				studentDto.getStudentName(),
				studentDto.getAdmissionsDay(),
				studentDto.getDepartment(),
				studentDto.getStudentRegistNum(),
				studentDto.getGrade(),
				studentDto.getGender(),
				studentDto.getStudentEmail(),
				studentDto.getStudentPhone(),
				studentDto.getStudentAddress(),
				studentDto.getNationality(),
				studentDto.getSchoolStatus());
		
		studentListRepository.save(foundStudent);
				
		return studentDto;
	}

}
