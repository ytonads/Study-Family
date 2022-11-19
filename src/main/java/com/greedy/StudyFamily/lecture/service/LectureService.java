package com.greedy.StudyFamily.lecture.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.dto.LectureWeekDto;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.entity.LectureWeek;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;
import com.greedy.StudyFamily.lecture.repository.LectureWeekRepository;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.professor.repository.ProfessorRepository;
import com.greedy.StudyFamily.student.dto.StudentDto;
import com.greedy.StudyFamily.student.entity.Student;
import com.greedy.StudyFamily.student.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LectureService {

	private final ProfessorRepository professorRepository;
	private final LectureRepository lectureRepository;
	private final StudentRepository studentRepository;
	private final LectureWeekRepository lectureWeekRepository;
	private final ModelMapper modelMapper;
	
	public LectureService
			(LectureWeekRepository lectureWeekRepository, ProfessorRepository professorRepository,LectureRepository lectureRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
		this.professorRepository = professorRepository;
		this.lectureRepository = lectureRepository;
		this.studentRepository = studentRepository;
		this.lectureWeekRepository = lectureWeekRepository;
		this.modelMapper = modelMapper;
	}

	
	
	//강좌 목록 조회 - 학생
	public Page<LectureDto> selectLectureStuList(int page, StudentDto student) {
		
		log.info("[LectureService] selectLectureStuList Start =====================" );
		
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("lectureCode").descending());
		
		/* 학생 엔티티 조회 */
		Student findStudent = studentRepository.findById(student.getStudentNo())
				.orElseThrow(() -> new IllegalArgumentException("해당 학생이 없습니다. studentNo= " + student.getStudentNo()));
		
		Page<Lecture> lectureStuList = lectureRepository.findByStudent(pageable, findStudent);
		Page<LectureDto> lectureDtoStuList = lectureStuList.map(lecture -> modelMapper.map(lecture, LectureDto.class));
		
		log.info("[ProductService] lectureDtoStuList : {}", lectureDtoStuList.getContent());
		log.info("[LectureService] selectLectureStuList End =====================" );
		
		return lectureDtoStuList;
	}
	
	

	//강좌 목록 조회 - 교수
	public Page<LectureDto> selectLectureProList(int page, ProfessorDto professor) {
		
		log.info("[LectureService] selectLectureProList Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("lectureCode").descending());
		
		/* 교수 엔티티 조회 */
		Professor findProfessor = professorRepository.findById(professor.getProfessorCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 교수가 없습니다. professorCode = " + professor.getProfessorCode()));
		

		Page<Lecture> lectureProList = lectureRepository.findByProfessor(pageable, findProfessor);
		Page<LectureDto> lectureDtoProList = lectureProList.map(lecture -> modelMapper.map(lecture, LectureDto.class));
		
		log.info("[ProductService] lectureDtoProList : {}", lectureDtoProList.getContent());
		log.info("[LectureService] selectLectureProList End =====================" );
		
		return lectureDtoProList;
	}




	

	
	



	
	

	
	
}
