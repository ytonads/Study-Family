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
import com.greedy.StudyFamily.professor.dto.ProfessorHistoryDto;
import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.professor.entity.ProfessorHistory;
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
		
		try {
			professorListRepository.save(modelMapper.map(professorDto, Professor.class));
		
		} catch (IOException e) {
			e.printStackTrace();
		}
				
		log.info("[ProfessorListService] insertProfessor End ================================= ");		
		return professorDto;
	}
	
	
	/* 교수 정보 수정 */
	@Transactional
	public ProfessorDto updateProfessor(ProfessorDto professorDto, List<ProfessorHistory> professorHistories) {
		
		log.info("[ProfessorListService] updeateProfessor Start ================================= ");	
		log.info("[ProfessorListService] updeateProfessor professorDto : {}", professorDto);	
		
		try {
			
			Professor oriProfessor = professorListRepository.findById(professorDto.getProfessorCode()).orElseThrow(
					() -> new IllegalArgumentException("해당 교수가 없습니다. professorCode=" + professorDto.getProfessorCode()));
	
			/* 조회한 기존 정보 수정 */
			oriProfessor.update(professorDto.getProfessorCode(),
					professorDto.getProfessorName(),
					professorDto.getProfessorPosition(),
					professorDto.getProfessorHireDate(),
					professorDto.getProfessorRegistNum(),
					professorDto.getProfessorPhone(),
					professorDto.getProfessorAddress(),
					professorDto.getProfessorStatus(),
					professorDto.getProfessorEmail(),
					modelMapper.map(professorDto.getDepartment(), Department.class)
					);
					
			professorListRepository.save(oriProfessor);
			
			
			
		
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		log.info("[ProfessorListService] updeateProfessor End ================================= ");	
		return professorDto;
	
	
	}
	
	
	
}
