package com.greedy.StudyFamily.student.dto;

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
	
	

}
