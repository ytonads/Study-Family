package com.greedy.StudyFamily.lecture.dto;

import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.subject.dto.SubjectDto;

import lombok.Data;

@Data
public class LectureDto {

	private Long lectureCode;
	private SubjectDto subCode;
	private Long capacity;
	private ProfessorDto professorCode;
	private String lectureName;
	private Long lecturePersonnel;
	private String openingDate;
	
}
