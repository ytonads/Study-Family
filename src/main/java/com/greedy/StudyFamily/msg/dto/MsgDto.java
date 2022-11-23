package com.greedy.StudyFamily.msg.dto;

import java.sql.Date;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.lecture.dto.LectureDto;

import lombok.Data;

@Data
public class MsgDto {
	
	private Long msgCode;
	private Date shipDate;
	private Date receiveDate;
	private String msgTitle;
	private String msgContent;
	private LectureDto lecture;
	private String msgStatus;
	private String deleteStatus;
	private LoginDto sender;
	private LoginDto receiver;
	
	
}
