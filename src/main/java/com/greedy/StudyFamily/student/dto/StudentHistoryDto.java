package com.greedy.StudyFamily.student.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class StudentHistoryDto {

	private Long studentModifyCode;
	private Date studentModifyDate;
	private StudentDto studentCode;
	private SchoolStatusDto schoolStatusCode;
	
}
