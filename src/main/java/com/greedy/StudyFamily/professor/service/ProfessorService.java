package com.greedy.StudyFamily.professor.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.exception.UserNotFoundException;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.professor.repository.ProfessorRepository;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfessorService {
	
	private final ProfessorRepository professorRepository;
	private final ModelMapper modelMapper;
	
	public ProfessorService(ProfessorRepository professorRepository
			              , ModelMapper modelMapper) {
		
		this.professorRepository = professorRepository;
		this.modelMapper = modelMapper;
	} 
	
	/* [교수] 내 정보 조회 */
	public ProfessorDto selectMyInfo(Long professorCode) {
		
		log.info("[ProfessorService] selectMyInfo Start ===========================");
		log.info("[ProfessorService] professorCode : {}", professorCode);
		
		Professor professor = professorRepository.findByProfessorCode(professorCode)
				.orElseThrow(() -> new UserNotFoundException(professorCode + "를 찾을 수 없습니다."));
		
		log.info("[ProfessorService] professor : {}", professor);
		
		log.info("[ProfessorService] selectMyInfo End ===========================");
		return modelMapper.map(professor, ProfessorDto.class);
	}
	
	/* [교수] 내 정보 - 개인정보 수정 */
	@Transactional
	public ProfessorDto updateProfessor(ProfessorDto professorDto) {
		
		log.info("[ProfessorService] updateProfessor Start ===================================");
		log.info("[ProfessorService] professorDto : {}", professorDto);
		
		Professor oriProfessor = professorRepository.findByProfessorCode(professorDto.getProfessorCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 교수가 없습니다. professorCode = " + professorDto.getProfessorCode()));
		
		oriProfessor.update(professorDto.getProfessorEmail()
				          , professorDto.getProfessorPhone()
				          , professorDto.getProfessorAddress());
		
		professorRepository.save(oriProfessor);
		
		return professorDto;
	}
	
}
