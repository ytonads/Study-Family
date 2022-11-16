package com.greedy.StudyFamily.lecture.dto;

import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Data;

@Data
public class AppClassDto {

	private StudentDto studentCode;
	private LectureDto lectureCode;
	
}
