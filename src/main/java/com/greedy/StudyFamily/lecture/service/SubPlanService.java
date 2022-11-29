package com.greedy.StudyFamily.lecture.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.lecture.dto.SubPlanDto;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.entity.SubPlan;
import com.greedy.StudyFamily.lecture.entity.SubPlanWrite;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;
import com.greedy.StudyFamily.lecture.repository.SubPlanRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubPlanService {
	
	private final SubPlanRepository subPlanRepository;
	private final LectureRepository lectureRepository;
	private final ModelMapper modelMapper;
	
	public SubPlanService(SubPlanRepository subPlanRepository, 
			LectureRepository lectureRepository, ModelMapper modelMapper) {
		this.subPlanRepository = subPlanRepository;
		this.modelMapper = modelMapper;
		this.lectureRepository = lectureRepository;
	}
	
	//수업계획서 조회
	public SubPlanDto selectPlanList(Long planCode) {
		
		log.info("[subPlanService] selectPlanList Start =====================" );
		log.info("[ProductService] planCode : {}", planCode);
		
		
		 SubPlan subPlan = subPlanRepository.findByPlanCode(planCode) .orElseThrow(()
		 -> new IllegalArgumentException("해당 수업계획서가 없습니다. planCode=" + planCode));
		 SubPlanDto subPlanDto = modelMapper.map(subPlan, SubPlanDto.class);

		log.info("[ProductService] subPlanDto : " + subPlanDto);
		
		log.info("[SubPlanService] selectPlanList End =====================" );
		
		return subPlanDto;
	}

	//수업계획서 작성
	@Transactional
	public SubPlanDto insertSubPlan(SubPlanDto subPlanDto, LoginDto loginUser) {
		log.info("[SubPlanService] insertSubPlan Start =========================");
		log.info("[SubPlanService] subPlanDto : {}", subPlanDto);
		log.info("[SubPlanService] subPlanDto : {}", subPlanDto.getLecture().getLectureCode());
		
		List<Lecture> lectureList = lectureRepository.findByProfessor(loginUser.getProfessor().getProfessorCode());
		  
		  Optional<Lecture> anyElement = lectureList.stream()
			        .filter(l -> l.getLectureCode() == subPlanDto.getLecture().getLectureCode()).findAny();
		  
		  if(anyElement.isEmpty()) {
				 throw new RuntimeException("교수번호와 강좌코드가 일치하지 않습니다.");
			  }
		  
		  else {
		SubPlanWrite subPlanWrite = new SubPlanWrite();
		subPlanWrite.setLectureCode(subPlanDto.getLecture().getLectureCode());
		subPlanWrite.setPurpose(subPlanDto.getPurpose());
		subPlanWrite.setPlanName(subPlanDto.getPlanName());
		subPlanWrite.setProfessorCode(subPlanDto.getProfessor().getProfessorCode());
		
		// 작성교수 정보 입력
		SubPlanWrite subPlan = subPlanRepository.save(subPlanWrite);

		  }
		log.info("[SubPlanService] insertSubPlan End =========================");
		
		return modelMapper.map(subPlanDto, SubPlanDto.class);
	}
}
