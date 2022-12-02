package com.greedy.StudyFamily.lecture.dto;

import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppClassesDto {

	private Long appClassCode;
	private LectureDto lecture;
	private StudentDto student;
	
	
}
