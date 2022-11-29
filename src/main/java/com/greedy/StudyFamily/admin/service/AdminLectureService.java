package com.greedy.StudyFamily.admin.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.repository.AdminLectureRepository;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.subject.dto.SubjectDto;
import com.greedy.StudyFamily.subject.entity.Department;
import com.greedy.StudyFamily.subject.entity.Subject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminLectureService {

	private final AdminLectureRepository lectureRepository;
	private final ModelMapper modelMapper;
	
	public AdminLectureService(AdminLectureRepository lectureRepository, ModelMapper modelMapper) {
		this.lectureRepository = lectureRepository;
		this.modelMapper = modelMapper;
	}
	
	
	
	/* <관리자> 강좌조회(강좌코드기준)*/
	public Page<LectureDto> codeLectureList(int page) {
		
		log.info("[AdminLectureRepository] ===============  Start");
		
		Pageable pageable = PageRequest.of(page -1, 5, Sort.by("lectureCode"));
		Page<Lecture> lectureList = lectureRepository.findAll(pageable);
		Page<LectureDto> lectureListDto = lectureList.map(lecture -> modelMapper.map(lecture, LectureDto.class));
		
		log.info("lectureListDtoList {} " + lectureListDto);
		
		
		log.info("[AdminLectureRepository] ===============  End");
		return lectureListDto;
		
	}

	/* <관리자> 강좌등록 */
	public LectureDto insertLectureList(LectureDto lecturedto) {
		
		log.info("InsertLectureList ==================== Start");
		log.info("lectureDto {} : " + lecturedto);
		
		lectureRepository.save(modelMapper.map(lecturedto, Lecture.class));
		
		log.info("InsertSubjectList ==================== End");
		
		return lecturedto;
	}


	/* <관리자> 강좌수정 */
	public LectureDto modifyLecture(LectureDto lectureDto) {
		
		log.info("ModifyLecture ====================  Start");
		log.info("lectureDto {} " + lectureDto);
		
	
		Lecture oriLecture = lectureRepository.findBylectureCode(lectureDto.getLectureCode());
		
		
		oriLecture.modifylecture(lectureDto.getLectureCode(), 
						modelMapper.map(lectureDto.getSubject(), Subject.class),
						lectureDto.getCapacity(),
						modelMapper.map(lectureDto.getProfessor(), Professor.class), 
						lectureDto.getLectureName(),
						lectureDto.getLecturePersonnel(),
						lectureDto.getOpeningDate()
						);
							
	
		log.info("ModifyLecture ====================  End");	
		
		return lectureDto;
		
	}



	public LectureDto deleteLecture(LectureDto removelecture) {
		
		log.info("deleteLecture ======================== Start");
		log.info("removelecture {} " + removelecture);
		
		LectureDto lectureDto = modelMapper.map(removelecture, LectureDto.class);
		lectureRepository.deleteById(lectureDto.getLectureCode());
		
		log.info("deleteSubject ======================== End");

		return lectureDto;
	}

	



}
