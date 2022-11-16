package com.greedy.StudyFamily.lecture.dto;

import com.greedy.StudyFamily.professor.dto.ProfessorDto;

import lombok.Data;

@Data
public class SubPlanDto {

	private Long planCode;
	private String purpose;
	private ProfessorDto professorCode;
	private LectureDto lectureCode;
	private String planName;
}
