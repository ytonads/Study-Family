package com.greedy.StudyFamily.lecture.service;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.board.dto.SubnoticeDto;
import com.greedy.StudyFamily.board.entity.Subnotice;
import com.greedy.StudyFamily.lecture.repository.SubnoticeRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubnoticeService {
	
	private final SubnoticeRepository subnoticeRepository;
	private final ModelMapper modelMapper;
	
	public SubnoticeService(SubnoticeRepository subnoticeRepository, ModelMapper modelMapper) {
		this.subnoticeRepository = subnoticeRepository;
		this.modelMapper = modelMapper;
	}

	
	
	//강좌공지 목록 조회
	public Page<SubnoticeDto> selectSubnoticeList(int page) {
		
		log.info("[SubnoticeService] selectSubnoticeList Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("subnoticeCode").descending());
		
		Page<Subnotice> subnoticeList = subnoticeRepository.findAll(pageable);
		Page<SubnoticeDto> subnoticeDtoList = subnoticeList.map(subnotice -> modelMapper.map(subnotice, SubnoticeDto.class));
		/* 클라이언트 측에서 서버에 저장 된 이미지 요청 시 필요한 주소로 가공 */
		//productDtoList.forEach(product -> product.setProductImageUrl(IMAGE_URL + product.getProductImageUrl()));
		
		log.info("[SubnoticeService] subnoticeDtoList : {}", subnoticeDtoList.getContent());
		
		log.info("[SubnoticeService] selectSubnoticeList End =====================" );
		
		return subnoticeDtoList;
	}
	
	
	
}
