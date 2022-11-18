package com.greedy.StudyFamily.lecture.dto;

import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Data;

@Data
public class EvalDto {

	private EvalStandardDto evalStandard;
	private StudentDto student;
	private LectureDto lecture;
	private Long evalGrade;
	
}
