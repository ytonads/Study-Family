package com.greedy.StudyFamily.admin.service;



import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.repository.AdminSubjectRepository;


import com.greedy.StudyFamily.subject.dto.SubjectDto;
import com.greedy.StudyFamily.subject.entity.Department;
import com.greedy.StudyFamily.subject.entity.Subject;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminSubjectService {

	private final AdminSubjectRepository subjectRepository; 
	private final ModelMapper modelMapper; 

	public AdminSubjectService(AdminSubjectRepository subjectRepository, ModelMapper modelMapper) {
		this.subjectRepository = subjectRepository;
		this.modelMapper = modelMapper;
	}
	
	/* <관리자> 과목 조회(과목코드기준)*/
	public Page<SubjectDto> subCodeSubjectList(int page) {

		Pageable pageable = PageRequest.of(page -1, 5, Sort.by("SubCode").descending());
		
		Page<Subject> subjectList = subjectRepository.findAll(pageable);
		Page<SubjectDto> subjectDtoList = subjectList.map(subject -> modelMapper.map(subject, SubjectDto.class));
		
		log.info("[QaService] qaBoardDtoList : {}" + subjectDtoList);
		
		return subjectDtoList;
	}

	/* <관리자> 과목 조회(과목명기준)*/
	public Page<SubjectDto> subTitleCodeSubjectList(int page) {
		
		Pageable pageable = PageRequest.of(page -1, 5, Sort.by("SubTitle").descending());
		
		Page<Subject> subjectList = subjectRepository.findAll(pageable);
		Page<SubjectDto> subjectDtoList = subjectList.map(subject -> modelMapper.map(subject, SubjectDto.class));
		
		log.info("[QaService] qaBoardDtoList : {}" + subjectDtoList);
		
		return subjectDtoList;		
	}
	
	/* <관리자> 과목 조회(학과기준)*/
	public Page<SubjectDto> departmentSubjectList(int page) {
		Pageable pageable = PageRequest.of(page -1, 5, Sort.by("department").descending());
		
		Page<Subject> subjectList = subjectRepository.findAll(pageable);
		Page<SubjectDto> subjectDtoList = subjectList.map(subject -> modelMapper.map(subject, SubjectDto.class));
		
		log.info("[QaService] qaBoardDtoList : {}" + subjectDtoList);
		
		return subjectDtoList;	
	
	}

	
	/* <관리자> 과목 등록*/
	public Object insertSubject(SubjectDto subjectDto) {
		
		log.info("InsertSubjectList ==================== Start");
		log.info("SubjectDto {} : " + subjectDto);
		
		subjectRepository.save(modelMapper.map(subjectDto, Subject.class));
		
		log.info("InsertSubjectList ==================== End");
		
		return subjectDto;
	}

	/* <관리자> 과목 수정*/
	public SubjectDto modifySubject(SubjectDto subjectDto) {

		log.info("ModifySubjectList ====================  Start");
		log.info("subjectDto {} " + subjectDto);
		
	
		Subject oriSubject = subjectRepository.findBysubCode(subjectDto.getSubCode());
		
		oriSubject.modify(subjectDto.getSubTitle(), 
					subjectDto.getMajorType(), 
					subjectDto.getSubGrade(), 
					modelMapper.map(subjectDto.getDepartment(), Department.class) 
					);
							
		log.info("ModifySubjectList ====================  End");	
		
		return subjectDto;
	}
	
    //먼저 entity로 가져온게 잘못 
	public SubjectDto deleteSubject(SubjectDto removeSubject) {
		
		log.info("deleteSubject ======================== Start");
		log.info("subjectDto {} " + removeSubject);
		
		SubjectDto subjectDto = modelMapper.map(removeSubject, SubjectDto.class);
		subjectRepository.deleteById(removeSubject.getSubCode());
		
		log.info("deleteSubject ======================== End");

		return subjectDto;
		
	}

}
