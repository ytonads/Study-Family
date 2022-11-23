package com.greedy.StudyFamily.lecture.service;


import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.exception.UserNotFoundException;
import com.greedy.StudyFamily.lecture.dto.AppClassDto;
import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.repository.AppClassRepository;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;
import com.greedy.StudyFamily.student.entity.Student;
import com.greedy.StudyFamily.student.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppClassService {
	
	private final AppClassRepository appClassRepository;
	private final LectureRepository lectureRepository;
	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;
	
	
	public AppClassService(AppClassRepository appClassRepository, LectureRepository lectureRepository, 
			StudentRepository studentRepository, ModelMapper modelMapper) {
		this.appClassRepository = appClassRepository;
		this.lectureRepository = lectureRepository;
		this.studentRepository = studentRepository;
		this.modelMapper = modelMapper;
	}

	//수강신청
	@Transactional
	public AppClassDto insertAppClass(AppClassDto appClassDto) {
		log.info("[AppClassService] insertAppClass Start =========================");
		log.info("[AppClassService] appClassDto : {}", appClassDto);
		
		// 신청학생 정보 입력
		AppClass appClass = appClassRepository.save(modelMapper.map(appClassDto, AppClass.class));
		
		// Lecture 테이블의 Lecture 조회하여 신청인원 업데이트
		Lecture foundLecture = lectureRepository.findByLectureCode(appClassDto.getLecture().getLectureCode());
				
		/*
		 * foundProduct.setProductStock(foundProduct.getProductStock() -
		 * purchaseDto.getOrderAmount()); stock : 상품재고
		 */
		
		//수강신청하면 신청인원 카운팅
		foundLecture.setLecturePersonnel(foundLecture.getLecturePersonnel() + 1);
		
		if(foundLecture.getLecturePersonnel() > foundLecture.getCapacity()) throw new RuntimeException("신청 인원 초과입니다.");
		
		log.info("[AppClassService] insertAppClass End =========================");
		
		return modelMapper.map(appClass, AppClassDto.class);
	}

	
	 

	
	
	
	
}
