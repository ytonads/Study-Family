package com.greedy.StudyFamily.student.dto;

import lombok.Data;

@Data
public class StudentDto {
	
	private Long studentCode;
	private String studentName;
	private String admissionsDay;
	private DepartmentDto departmentCode;
	private String studentRegistNum;
	private String grade;
	private String gender;
	private String studentEmail;
	private String studentPhone;
	private String studentAddress;
	private String nationality;
	
	

}
