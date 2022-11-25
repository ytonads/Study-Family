package com.greedy.StudyFamily.board.service;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.board.dto.SubNoticeDto;
import com.greedy.StudyFamily.board.entity.SubNotice;
import com.greedy.StudyFamily.board.entity.SubNoticeWrite;
import com.greedy.StudyFamily.board.repository.SubnoticeRepository;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class SubnoticeService {
	
	private final SubnoticeRepository subnoticeRepository;
	private final LectureRepository lectureRepository;
	private final ModelMapper modelMapper;
	
	/*
	 * @Value("${image.image-dir}") private String IMAGE_DIR;
	 * 
	 * @Value("${image.image-url}") private String IMAGE_URL;
	 */
	
	public SubnoticeService(SubnoticeRepository subnoticeRepository, ModelMapper modelMapper
			,LectureRepository lectureRepository) {
		this.subnoticeRepository = subnoticeRepository;
		this.lectureRepository = lectureRepository;
		this.modelMapper = modelMapper;
	}

	
	
	//강좌공지 목록 조회
	public Page<SubNoticeDto> selectSubnoticeList(int page) {
		
		log.info("[SubnoticeService] selectSubnoticeList Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("subnoticeCode").descending());
		
		Page<SubNotice> subnoticeList = subnoticeRepository.findByStatus(pageable, "Y");
		Page<SubNoticeDto> subnoticeDtoList = subnoticeList.map(subNotice -> modelMapper.map(subNotice, SubNoticeDto.class));
		/* 클라이언트 측에서 서버에 저장 된 이미지 요청 시 필요한 주소로 가공 */
		//subnoticeDtoList.forEach(subNotice -> subNotice.setFileCode(IMAGE_URL + subNotice.getFileCode()));
		
		log.info("[SubnoticeService] subnoticeDtoList : {}", subnoticeDtoList.getContent());
		
		log.info("[SubnoticeService] selectSubnoticeList End =====================" );
		
		return subnoticeDtoList;
	}


	//강좌공지 상세 조회
	public SubNoticeDto selectSubNotice(Long subnoticeCode) {
		
		log.info("[SubNoticeService] selectSubNotice Start =====================" );
		log.info("[SubNoticeService] subnoticeCode : {}", subnoticeCode);
		
		SubNotice subNotice = subnoticeRepository.findBySubNoticeCode(subnoticeCode)
				.orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다. subnoticeCode=" + subnoticeCode));
		SubNoticeDto subNoticeDto = modelMapper.map(subNotice, SubNoticeDto.class);
		
        log.info("[SubNoticeService] subNoticeDto : " + subNoticeDto);
		
		log.info("[SubNoticeService] selectSubNotice End =====================" );
		
		
		return subNoticeDto;
	}

	//강좌공지 작성
	  @Transactional 
	  public SubNoticeDto insertSubnotice(SubNoticeDto subNoticeDto, LoginDto loginUser)
	  {
	  
	  log.info("[SubNoticeService] insertSubnotice Start ========================="); 
	  log.info("[SubNoticeService] subNoticeDto : {}", subNoticeDto);
	  log.info("[SubNoticeService] subNoticeDto : {}", subNoticeDto.getLecture().getLectureCode());

	  List<Lecture> lectureList = lectureRepository.findByProfessor(loginUser.getProfessor().getProfessorCode());
	  
	  Optional<Lecture> anyElement = lectureList.stream()
		        .filter(l -> l.getLectureCode() == subNoticeDto.getLecture().getLectureCode()).findAny();
	  
	  
	  if(anyElement.isEmpty()) {
		 throw new RuntimeException("교수번호와 강좌코드가 일치하지 않습니다.");
	  }
	  
	  else {
		  
	  SubNoticeWrite subnotice = new SubNoticeWrite();
	  subnotice.setSubnoticeTitle(subNoticeDto.getSubnoticeTitle());
	  subnotice.setContent(subNoticeDto.getContent());
	  subnotice.setLecture(subNoticeDto.getLecture().getLectureCode());
	  
	  SubNoticeWrite subnoticeWrite = subnoticeRepository.save(subnotice);	  
	  }
	  
	  log.info("[SubNoticeService] insertSubnotice End =========================");
	  
	  return modelMapper.map(subNoticeDto, SubNoticeDto.class);
	  }
	 


	//강좌공지 수정
	@Transactional
	public SubNoticeDto updateSubnotice(SubNoticeDto subNoticeDto, LoginDto loginUser) {

		 log.info("[SubNoticeService] updateSubnotice Start ========================="); 
		  log.info("[SubNoticeService] subNoticeDto : {}", subNoticeDto);
		  log.info("[SubNoticeService] subNoticeDto : {}", subNoticeDto.getLecture().getLectureCode());

		  
			
			  List<Lecture> lectureList =
			  lectureRepository.findByProfessor(loginUser.getProfessor().getProfessorCode());
			  
			  Optional<Lecture> anyElement = lectureList.stream().
					  filter(l -> l.getLectureCode() == subNoticeDto.getLecture().getLectureCode()).findAny();
			  
			  if(anyElement.isEmpty()) { 
				  throw new RuntimeException("교수번호와 강좌코드가 일치하지 않습니다."); 
			  }

			  else {
			 
			 
				/*
				 * SubNoticeWrite subnotice = new SubNoticeWrite();
				 * subnotice.setSubnoticeTitle(subNoticeDto.getSubnoticeTitle());
				 * subnotice.setContent(subNoticeDto.getContent());
				 * subnotice.setLecture(subNoticeDto.getLecture().getLectureCode());
				 */
		SubNotice subnotice = subnoticeRepository.findById(subNoticeDto.getSubnoticeCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 공지사항이 없습니다. subnoticeCode=" + subNoticeDto.getSubnoticeCode()));

		subnotice.update(subNoticeDto.getSubnoticeTitle(), subNoticeDto.getContent());
				
		 subnoticeRepository.save(subnotice);
			/* } */
			  }
		 log.info("[SubNoticeService] updateSubnotice End =========================");
		 
		return subNoticeDto;
	}


	//강좌공지 삭제
	public void deleteSubnotice(Long subnoticeCode) {
		
		log.info("[AppClassService] deleteSubnotice Start =========================");
		log.info("[AppClassService] subnoticeDto : {}", subnoticeCode);
		
		SubNotice foundSubnotice = subnoticeRepository.findById(subnoticeCode)
				.orElseThrow(() -> new RuntimeException("존재하지 않는 강좌 공지사항입니다."));
			 
		subnoticeRepository.delete(foundSubnotice);
		
		log.info("[AppClassService] deleteSubnotice End =========================");
		
	}





	
	
}
