package com.greedy.StudyFamily.msg.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.admin.repository.LoginRepository;
import com.greedy.StudyFamily.lecture.dto.AppClassDto;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.repository.AppClassRepository;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;
import com.greedy.StudyFamily.msg.dto.MsgDto;
import com.greedy.StudyFamily.msg.entity.Msg;
import com.greedy.StudyFamily.msg.repository.MsgRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MsgService {
	
	private final MsgRepository msgRepository;
	private final LoginRepository loginRepository;
	private final LectureRepository lectureRepository;
	private final AppClassRepository appClassRepository;
	private final ModelMapper modelMapper;
	
	public MsgService(AppClassRepository appClassRepository, LectureRepository lectureRepository, 
			MsgRepository msgRepository, LoginRepository loginRepository, ModelMapper modelMapper) {
		this.loginRepository = loginRepository;
		this.msgRepository = msgRepository;
		this.lectureRepository = lectureRepository;
		this.appClassRepository = appClassRepository;
		this.modelMapper = modelMapper;
	}
	
	
	
	//강좌코드로 수강 학생 리스트 조회 - AppClass 엔티티 기준
	public Page<AppClassDto> selectStudentListByLectureCode(int page, LectureDto lecture) {
		log.info("[MsgService] selectStudentListByLectureCode Start =======================");
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("student.studentNo").descending());
		
		//강좌 엔티티 조회
		Lecture findLecture = lectureRepository.findById(lecture.getLectureCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 강좌가 존재하지 않습니다. lectureCode= " + lecture.getLectureCode()));
		
		Page<AppClass> appClassList = appClassRepository.findByLecture(pageable, findLecture);
		Page<AppClassDto> appClassDtoList = appClassList.map(appClass -> modelMapper.map(appClass, AppClassDto.class));
		
		log.info("[MsgService] appClassDtoList : {}", appClassDtoList.getContent());
		
		log.info("[MsgService] selectStudentListByLectureCode End =======================");
		return appClassDtoList;
	}

	
	
	
	
	//쪽지 발송
	@Transactional
	public MsgDto write(MsgDto msgDto) {
		log.info("[MsgService] write Start =======================");
		log.info("[MsgService] msgDto : {}", msgDto);
		
		
		Login receiver = loginRepository.findByLoginId(msgDto.getReceiverId());
		Login sender = loginRepository.findByLoginId(msgDto.getSenderId());
		
		
		Msg msg = new Msg();
		msg.setSender(sender);
		msg.setReceiver(receiver);
		
		msg.setMsgTitle(msgDto.getMsgTitle());
		msg.setMsgContent(msgDto.getMsgContent());
		msg.setShipDate(msgDto.getShipDate());
//		msg.setReceiveDate(msgDto.getReceiveDate());
		msgRepository.save(msg);
		
		log.info("[MsgService] msg : {}", msg);
		log.info("[MsgService] write End =======================");
		return null;
	}

	
	
	//받은 편지함
	public List<MsgDto> receivedMessage(LoginDto login) {
		log.info("[MsgService] receivedMessage Start =======================");
		log.info("[MsgService] loginId : {}", login);
		
//		Login login = loginRepository.findByLoginId(loginId)
//				.orElseThrow(() -> new UserNotFoundException("해당 유저가 존재하지 않습니다."));
//		
//		List<MsgDto> msgList = msgRepository.findByReceiver(login, Sort.by(Sort.Direction.DESC, "msgCode"))
//				.stream().map(msg -> modelMapper.map(msg, MsgDto.class)).collect(Collectors.toList()); 
		
		
		List<Msg> msgs = msgRepository.findAllByReceiver(login);
		List<MsgDto> msgDtos = new ArrayList<>();
		
		log.info("[MsgService] receivedMessage End =======================");
		return msgDtos;
	}

	
	
	
	
}
