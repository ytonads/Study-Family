package com.greedy.StudyFamily.lecture.dto;

import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Data;

@Data
public class CourseHistoryDto {

	private StudentDto student;
	private Long courseTime;
	private Long courseCode;
	private LectureWeekDto lectureWeek;
	private String courseStatus;
	
}
