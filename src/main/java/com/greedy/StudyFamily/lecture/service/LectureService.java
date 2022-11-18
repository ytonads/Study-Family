package com.greedy.StudyFamily.lecture.service;

import java.util.UUID;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LectureService {
	
	private final LectureRepository lectureRepository;
	private final ModelMapper modelMapper;
	
	public LectureService(LectureRepository lectureRepository, ModelMapper modelMapper) {
		this.lectureRepository = lectureRepository;
		this.modelMapper = modelMapper;
	}
	
	
	@Value("${image.image-dir}")
	private String IMAGE_DIR;
	@Value("${image.image-url}")
	private String IMAGE_URL;

	

	//강좌 코드 기준으로 강의실 상세 조회(학생)
	public Object selectLecture(Long lectureCode) {
		
		log.info("[LectureService] selectLecture Start ===========");
		log.info("[LectureService] lectureCode : {}", lectureCode);
		
		Lecture lecture = lectureRepository.findByLectureCode(lectureCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 강의실이 존재하지 않습니다. lectureCode=" + lectureCode));
		LectureDto lectureDto = modelMapper.map(lecture, LectureDto.class);
		/* 파일 다뤄주는 코드 - 작업 진행 중 (file 테이블에서 OneToMany로 댕겨 올듯?)*/
		//lectureDto.setLectureFileUrl( + lectureDto.getLectureFileUrl());
		
		log.info("[LectureService] lectureDto : {}", lectureDto);
		
		log.info("[LectureService] selectLecture End ===========");
		
		return lectureDto;
	}
	
	

	//강좌 코드 기준으로 강의실 상세 조회(교수)
	public Object selectLectureForProfessor(Long lectureCode) {
		
		log.info("[LectureService] selectLectureForProfessor Start ===========");
		log.info("[LectureService] lectureCode : {}", lectureCode);
		
		Lecture lecture = lectureRepository.findById(lectureCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 강의실이 존재하지 않습니다. lectureCode=" + lectureCode));
		LectureDto lectureDto = modelMapper.map(lecture, LectureDto.class);
		/* 파일 다뤄주는 코드 - 작업 진행 중 (file 테이블에서 OneToMany로 댕겨 올듯?)*/
		//lectureDto.setLectureFileUrl( + lectureDto.getLectureFileUrl());
		
		log.info("[LectureService] lectureDto : {}", lectureDto);
		
		log.info("[LectureService] selectLectureForProfessor End ===========");
		return null;
	}

	
	
	//수업 자료 등록(교수)
	public Object insertFile(LectureDto lectureDto) {
		
		log.info("[LectureService] insertFile Start ===========");
		log.info("[LectureService] lectureDto : {}", lectureDto);
		
		String imageName = UUID.randomUUID().toString().replace("-", "");
		String replaceFileName = null;
		
		//replaceFileName = FileUploadUtils.saveFile(IMAGE_DIR, imageName, lectureDto.getLectureImage());
		
		
		log.info("[LectureService] insertFile End ===========");
		return null;
	}

}
