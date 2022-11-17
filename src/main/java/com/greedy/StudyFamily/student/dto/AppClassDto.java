package com.greedy.StudyFamily.student.dto;

import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.subject.dto.SubjectDto;

import lombok.Data;

@Data
public class AppClassDto {

	private SubjectDto majorType;
	private SubjectDto subCode;
	private DepartmentDto departmentName;
	private SubjectDto subTitle;
	private ProfessorDto professorName;
	private LectureDto lecturePersonnel;
	private LectureDto capacity;
}
