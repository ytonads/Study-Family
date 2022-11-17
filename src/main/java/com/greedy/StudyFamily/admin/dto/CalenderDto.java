package com.greedy.StudyFamily.admin.dto;

import java.sql.Date;

import com.greedy.StudyFamily.student.dto.DepartmentDto;

import lombok.Data;

@Data
public class CalenderDto {

	private Long calenderCode;
	private Date calenderDate;
	private String calenderContent;
	private String calenderStatus;
	private String calenderType;
	private DepartmentDto departmentCode;
	
}
