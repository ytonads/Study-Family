package com.greedy.StudyFamily.admin.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.repository.AdminLectureRepository;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.Lecture;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminLectureService {

	private final AdminLectureRepository lectureRepository;
	
	public AdminLectureService(AdminLectureRepository lectureRepository) {
		this.lectureRepository = lectureRepository;
	}
	
	
	
	public Page<LectureDto> codeLectureList(int page) {
		
		Pageable pageable = PageRequest.of(page -1, 5, Sort.by("lectureCode"));
		

		
		return null;
	}

}
