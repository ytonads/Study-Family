package com.greedy.StudyFamily.board.dto;

import java.sql.Date;

import com.greedy.StudyFamily.subject.dto.DepartmentDto;

import lombok.Data;

@Data
public class CalenderDto {

	private Long calenderCode;
	private Date calenderDate;
	private String calenderContent;
	private String calenderStatus;
	private String calenderType;
	private DepartmentDto department;
	
}
