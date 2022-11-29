package com.greedy.StudyFamily.professor.dto;


import com.greedy.StudyFamily.subject.dto.DepartmentDto;

import lombok.Data;

@Data
public class ProfessorDto{
	
	private Long professorCode;
	private String professorName;
	private String professorPosition;
	private String professorHireDate;
	private String professorRegistNum;
	private String professorPhone;
	private String professorAddress;
	private String professorStatus;
	private String professorEmail;
	private DepartmentDto department;
	
}
