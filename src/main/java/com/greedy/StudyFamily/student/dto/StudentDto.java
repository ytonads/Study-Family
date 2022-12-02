package com.greedy.StudyFamily.student.dto;

import java.util.List;

import com.greedy.StudyFamily.lecture.dto.AppClassDto;
import com.greedy.StudyFamily.subject.dto.DepartmentDto;

import lombok.Data;

@Data
public class StudentDto {
	
	private Long studentNo;
	private String studentCode;
	private String studentName;
	private String admissionsDay;
	private DepartmentDto department;
	private String studentRegistNum;
	private String grade;
	private String gender;
	private String studentEmail;
	private String studentPhone;
	private String studentAddress;
	private String nationality;
	
	private List<AppClassDto> appClasses;
	
	/* 추가 */
	private SchoolStatusDto schoolStatus;
	
	

}
