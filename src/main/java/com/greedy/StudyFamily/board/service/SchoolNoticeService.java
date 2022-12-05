package com.greedy.StudyFamily.board.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.board.dto.SchoolNoticeDto;
import com.greedy.StudyFamily.board.entity.SchoolNotice;
import com.greedy.StudyFamily.board.repository.SchoolNoticeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SchoolNoticeService {
	
	private final SchoolNoticeRepository schoolNoticeRepository;
	private final ModelMapper modelMapper;
	
	public SchoolNoticeService(SchoolNoticeRepository schoolNoticeRepository, ModelMapper modelMapper) {
		this.schoolNoticeRepository = schoolNoticeRepository;
		this.modelMapper = modelMapper;
	}

	/* 공지 목록 조회 */
	public Page<SchoolNoticeDto> selectSchoolNoticeList(int page){
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("schoolNoticeCode").descending());
		
		Page<SchoolNotice> schoolNoticeList = schoolNoticeRepository.findBySchoolNoticeState(pageable, "Y");
		Page<SchoolNoticeDto> schoolNoticeDtoList = schoolNoticeList.map(schoolNotice -> modelMapper.map(schoolNotice, SchoolNoticeDto.class));
	
		return schoolNoticeDtoList;
	}
	
	/* 공지 목록 조회 - 검색 */
	public Page<SchoolNoticeDto> selectSchoolNoticeListBySchoolNoticeCategory(int page, String schoolNoticeCategory){
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("schoolNoticeCode").descending());
		
		Page<SchoolNotice> schoolNoticeList = schoolNoticeRepository.findBySchoolNoticeCategoryAndSchoolNoticeState(pageable, schoolNoticeCategory, "Y");
		Page<SchoolNoticeDto> schoolNoticeDtoList = schoolNoticeList.map(schoolNotice -> modelMapper.map(schoolNotice, SchoolNoticeDto.class));
		
		return schoolNoticeDtoList;
	}
	
	
	/* 공지 상세 조회 */
	public SchoolNoticeDto selectSchoolNotice(Long schoolNoticeCode) {
		
		SchoolNotice schoolNotice = schoolNoticeRepository.findBySchoolNoticeCode(schoolNoticeCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 글이 없습니다. schoolNoticeCode=" + schoolNoticeCode));
		SchoolNoticeDto schoolNoticeDto = modelMapper.map(schoolNotice, SchoolNoticeDto.class);
		
		
		return schoolNoticeDto;
	}
	
	
	/* 공지 등록 */
	@Transactional
	public SchoolNoticeDto insertSchoolNotice(SchoolNoticeDto schoolNoticeDto) {
		
		log.info("[SchoolNoticeService] insertSchoolNotice Start ======================================= ");
		log.info("[SchoolNoticeService] SchoolNoticeDto : {}", schoolNoticeDto);
		
		schoolNoticeRepository.save(modelMapper.map(schoolNoticeDto, SchoolNotice.class));
		
		return schoolNoticeDto;
	}
	
	
	/* 공지 수정 */
	@Transactional
	public SchoolNoticeDto updateSchoolNotice(SchoolNoticeDto schoolNoticeDto) {
		
		SchoolNotice foundSchoolNotice = schoolNoticeRepository.findById(schoolNoticeDto.getSchoolNoticeCode())
				.orElseThrow(() -> new RuntimeException ("존재하지 않는 글 입니다."));
		
		foundSchoolNotice.update(
				schoolNoticeDto.getSchoolNoticeTitle(),
				schoolNoticeDto.getSchoolNoticeContent(),
				schoolNoticeDto.getSchoolNoticeState(),
				schoolNoticeDto.getSchoolNoticeCategory(),
				schoolNoticeDto.getDepartment());
		
		schoolNoticeRepository.save(foundSchoolNotice);
		
		return schoolNoticeDto;
		
	}
	
	
	
	
	
	
	
}
