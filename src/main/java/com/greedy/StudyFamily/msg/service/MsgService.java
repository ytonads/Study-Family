package com.greedy.StudyFamily.msg.service;


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
import com.greedy.StudyFamily.subject.entity.Department;
import com.greedy.StudyFamily.subject.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MsgService {
	
	private final MsgRepository msgRepository;
	private final LoginRepository loginRepository;
	private final LectureRepository lectureRepository;
	private final AppClassRepository appClassRepository;
	private final DepartmentRepository departmentRepository;
	private final ModelMapper modelMapper;
	
	public MsgService(AppClassRepository appClassRepository, LectureRepository lectureRepository, 
			MsgRepository msgRepository, LoginRepository loginRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
		this.loginRepository = loginRepository;
		this.msgRepository = msgRepository;
		this.lectureRepository = lectureRepository;
		this.appClassRepository = appClassRepository;
		this.departmentRepository = departmentRepository;
		this.modelMapper = modelMapper;
	}
	
	
	
	/* 수강생 리스트 조회 - 완료!!! */
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
	
	

	
	
	/* 쪽지 발송 - 완료!!! */
	@Transactional
	public MsgDto write(MsgDto msgDto, LoginDto senderDto) {
		
		log.info("[MsgService] write Start =======================");
		log.info("[MsgService] msgDto : {}", msgDto);
		
		//수신자 조회
		Login receiver = loginRepository.findById(msgDto.getReceiver().getLoginCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 수신자가 존재하지 않습니다."));
		
		//강좌 조회
		Lecture lecture = lectureRepository.findById(msgDto.getLecture().getLectureCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 강좌가 존재하지 않습니다."));
		
		//DB 저장
		Msg newMsg = new Msg();
		newMsg.setReceiver(receiver);
		newMsg.setSender(modelMapper.map(senderDto, Login.class));
		newMsg.setMsgTitle(msgDto.getMsgTitle());
		newMsg.setMsgContent(msgDto.getMsgContent());
		newMsg.setMsgStatus(msgDto.getMsgStatus());
		newMsg.setLecture(lecture);
		msgRepository.save(newMsg);
		
		log.info("[MsgService] write End =======================");
		return modelMapper.map(newMsg, MsgDto.class);
	}



	//쪽지 수신함 조회
	public Page<MsgDto> selectReceivedBox(int page, LoginDto receiver) {
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("msgCode").descending());
		
		Page<Msg> receivedMsgs = msgRepository.findReceivedMsgs(pageable, modelMapper.map(receiver, Login.class));
		Page<MsgDto> receivedMsgDtoList = receivedMsgs.map(msg -> modelMapper.map(msg, MsgDto.class));
		
		return receivedMsgDtoList;
	}


	//쪽지 발신함 조회
	public Page<MsgDto> selectSendedBox(int page, LoginDto sender) {

		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("msgCode").descending());
		
		Page<Msg> sendeddMsgs = msgRepository.findSendedMsgs(pageable, modelMapper.map(sender, Login.class));
		Page<MsgDto> sendedMsgDtoList = sendeddMsgs.map(msg -> modelMapper.map(msg, MsgDto.class));
		
		return sendedMsgDtoList;
	}

	
	
	

	
	
	
	
}
