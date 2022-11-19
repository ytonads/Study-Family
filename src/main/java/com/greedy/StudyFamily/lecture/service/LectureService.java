package com.greedy.StudyFamily.lecture.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.repository.AppclassRepository;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.professor.repository.ProfessorRepository;
import com.greedy.StudyFamily.student.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LectureService {

	private final ProfessorRepository professorRepository;
	private final AppclassRepository appclassRepository;
	private final LectureRepository lectureRepository;
	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;
	
	public LectureService
			(ProfessorRepository professorRepository, AppclassRepository appclassRepository, LectureRepository lectureRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
		this.professorRepository = professorRepository;
		this.appclassRepository = appclassRepository;
		this.lectureRepository = lectureRepository;
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
	}

	

	//강좌 목록 조회 - 교수
	public Page<LectureDto> selectLectureProList(int page, ProfessorDto professor) {
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("lectureCode").descending());
		
		/* 교수 엔티티 조회 */
		Professor findProfessor = professorRepository.findById(professor.getProfessorCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 카테고리가 없습니다. professorCode = " + professor.getProfessorCode()));
		

		Page<Lecture> lectureProList = lectureRepository.findByProfessor(pageable, findProfessor);
		Page<LectureDto> lectureDtoProList = lectureProList.map(lecture -> modelMapper.map(lecture, LectureDto.class));
		
		
		return lectureDtoProList;
	}

	
	



	
	

	
	
}
