package com.greedy.StudyFamily.professor.service;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.sql.Update;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.professor.repository.ProfessorListRepository;
import com.greedy.StudyFamily.subject.entity.Department;

import io.jsonwebtoken.io.IOException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfessorListService {

	private final ProfessorListRepository professorListRepository;
	private final ModelMapper modelMapper;

	
	public ProfessorListService(ProfessorListRepository professorListRepository, ModelMapper modelMapper) {
		this.professorListRepository = professorListRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 교수 목록 조회 */
	public Page<ProfessorDto> selectProfessorListForAdmin(int page) {
		
		log.info("[ProfessorListService] selectProfessorListForAdmin Start =============================");
		Pageable pageable = PageRequest.of(page -1, 10, Sort.by("ProfessorCode").descending());
		
		Page<Professor> professorList = professorListRepository.findAll(pageable);
		Page<ProfessorDto> professorDtoList = professorList.map(professor -> modelMapper.map(professor, ProfessorDto.class));
		
		log.info("[ProfessorListService] professorDtoList : {} ", professorDtoList.getContent());
		log.info("[ProfessorListService] selectProfessorListForAdmin End =============================== ");
		
		return professorDtoList;	
		
	}
	
	/* 교수 상세 조회 */
	public ProfessorDto selectProfessorListForAdmin(Long professorCode) {
		
		log.info("[ProfessorListService] selectProfessorForAdmin Start ================================= ");
		log.info("[ProfessorListService] professorCode : " + professorCode);
		
		Professor professor = professorListRepository.findById(professorCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 교수가 없습니다. professorCode =" + professorCode));
		ProfessorDto professorDto = modelMapper.map(professor, ProfessorDto.class);
		
		return professorDto;
	}
	
	/* 교수 정보 등록 */
	@Transactional
	public ProfessorDto insertProfessor(ProfessorDto professorDto) {
		
		log.info("[ProfessorListService] insertProfessor Start ================================= ");
		log.info("[ProfessorListService] professorDto : {}", professorDto);
		
		professorListRepository.save(modelMapper.map(professorDto, Professor.class));
				
		log.info("[ProfessorListService] insertProfessor End ================================= ");		
		return professorDto;
	}
	
	
	/* 교수 정보 수정 */
	@Transactional
	public ProfessorDto updateProfessor(ProfessorDto professorDto) {
		
		Professor foundProfessor = professorListRepository.findById(professorDto.getProfessorCode())
				.orElseThrow(() -> new RuntimeException ("존재하지 않는 교수 입니다."));
		
		foundProfessor.update(
				professorDto.getProfessorName(),
				professorDto.getProfessorPosition(),
				professorDto.getProfessorHireDate(),
				professorDto.getProfessorRegistNum(),
				professorDto.getProfessorPhone(),
				professorDto.getProfessorAddress(),
				professorDto.getProfessorStatus(),
				professorDto.getProfessorEmail(),
				professorDto.getDepartment());
		
			professorListRepository.save(foundProfessor);
				
		log.info("[ProfessorListService] updeateProfessor End ================================= ");	
		return professorDto;
	
	
	}

	
}
