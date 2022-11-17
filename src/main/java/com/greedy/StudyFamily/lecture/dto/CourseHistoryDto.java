package com.greedy.StudyFamily.lecture.dto;

import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Data;

@Data
public class CourseHistoryDto {

	private StudentDto studentNo;
	private Long courseTime;
	private Long courseCode;
	private LectureWeekDto lectureWeekCode;
	private String courseStatus;
	
}
