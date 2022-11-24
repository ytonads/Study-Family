package com.greedy.StudyFamily.lecture.service;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.lecture.dto.SubPlanDto;
import com.greedy.StudyFamily.lecture.entity.SubPlan;
import com.greedy.StudyFamily.lecture.repository.SubPlanRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubPlanService {
	
	private final SubPlanRepository subPlanRepository;
	private final ModelMapper modelMapper;
	
	public SubPlanService(SubPlanRepository subPlanRepository, ModelMapper modelMapper) {
		this.subPlanRepository = subPlanRepository;
		this.modelMapper = modelMapper;
	}
	
	//수업계획서 조회
	public SubPlanDto selectPlanList(Long planCode) {
		
		log.info("[subPlanService] selectPlanList Start =====================" );
		log.info("[ProductService] planCode : {}", planCode);
		
		SubPlan subPlan = subPlanRepository.findByPlanCode(planCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 수업계획서가 없습니다. planCode=" + planCode));
		SubPlanDto subPlanDto = modelMapper.map(subPlan, SubPlanDto.class);
		
		log.info("[ProductService] subPlanDto : " + subPlanDto);
		
		log.info("[SubPlanService] selectPlanList End =====================" );
		
		return subPlanDto;
	}
}
