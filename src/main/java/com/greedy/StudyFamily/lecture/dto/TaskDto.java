package com.greedy.StudyFamily.lecture.dto;

import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Data;

@Data
public class TaskDto {

	private Long taskCode;
	private LectureDto lectureCode;
	private StudentDto studentCode;
	
}
