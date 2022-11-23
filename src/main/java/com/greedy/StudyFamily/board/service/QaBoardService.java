package com.greedy.StudyFamily.board.service;

import org.modelmapper.ModelMapper;



import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.board.dto.QaBoardDto;
import com.greedy.StudyFamily.board.entity.QaBoard;
import com.greedy.StudyFamily.board.repository.QaBoardRepository;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Service
public class QaBoardService {

	private final QaBoardRepository qaboardRepository;
	private final ModelMapper modelMapper;
	
	
	public QaBoardService(QaBoardRepository qaboardRepository, ModelMapper modelMapper) {
		this.qaboardRepository = qaboardRepository;
		this.modelMapper = modelMapper;
	}
	
	
	public Page<QaBoardDto> InquiryBoardList(int page) {
		
		Pageable pageable = PageRequest.of(page -1, 5, Sort.by("qaCode").descending() );
		
		Page<QaBoard> qaBoardList = qaboardRepository.findByqaStatus(pageable, "Y");
		Page<QaBoardDto> qaBoardDtoList = qaBoardList.map(qaboard -> modelMapper.map(qaboard, QaBoardDto.class));
		
		log.info("[QaService] qaBoardDtoList : {}" + qaBoardDtoList);
		
		return qaBoardDtoList;
	}

	
}
