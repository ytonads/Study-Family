package com.greedy.StudyFamily.subject.dto;

import lombok.Data;

@Data
public class SubjectDto {

	private Long subCode;
	private String subTitle;
	private String majorType;
	private Long subGrade;
	private DepartmentDto department;
	
}
