package com.greedy.StudyFamily.lecture.dto;

import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Data;

@Data
public class EvalDto {

	private EvalStandardDto evalStandardCode;
	private StudentDto studentCode;
	private LectureDto lectureCode;
	private Long evalGrade;
	
}
