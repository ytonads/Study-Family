package com.greedy.StudyFamily.professor.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.exception.UserNotFoundException;
import com.greedy.StudyFamily.lecture.dto.AppClassDto;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.repository.AppClassRepository;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.professor.repository.ProfessorRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	private final LectureRepository lectureRepository;
	private final AppClassRepository appClassRepository;
	private final ModelMapper modelMapper;
	
	public ProfessorService(ProfessorRepository professorRepository
			              , ModelMapper modelMapper
			              , LectureRepository lectureRepository
			              , AppClassRepository appClassRepository) {
		
		this.professorRepository = professorRepository;
		this.lectureRepository = lectureRepository;
		this.appClassRepository = appClassRepository;
		this.modelMapper = modelMapper;
	} 
	
	/* 태익 - [교수] 내 정보 조회 */
	@Transactional
	public ProfessorDto selectMyInfo(Long professorCode) {
		
		log.info("[ProfessorService] selectMyInfo Start ===========================");
		log.info("[ProfessorService] professorCode : {}", professorCode);
		
		Professor professor = professorRepository.findByProfessorCode(professorCode);
		
		log.info("[ProfessorService] professor : {}", professor);
		log.info("[ProfessorService] selectMyInfo End ============================");
		
		return modelMapper.map(professor, ProfessorDto.class);

	}
	
	/* 태익 - [교수] 개인 정보 수정 메소드 */
	@Transactional
	public ProfessorDto updateProfessor(ProfessorDto professorDto) {
		
		log.info("[ProfessorService] updateProfessor Start ===================================");
		log.info("[ProfessorService] professorDto : {}", professorDto);
		
		Professor oriProfessor = professorRepository.findById(professorDto.getProfessorCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 교수가 없습니다. professorCode = " + professorDto.getProfessorCode()));
		
		oriProfessor.update2(professorDto.getProfessorCode(),
							 professorDto.getProfessorPhone(),
							 professorDto.getProfessorAddress(),
							 professorDto.getProfessorEmail());
		
		professorRepository.save(oriProfessor);
		
		return professorDto;
	}
	
}
