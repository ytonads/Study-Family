package com.greedy.StudyFamily.student.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.student.dto.DepartmentDto;
import com.greedy.StudyFamily.subject.dto.SubjectDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_APP_CLASS")
@DynamicInsert
public class AppClass {

	@Id
	@JoinColumn(name = "MAJOR_TYPE")
	private SubjectDto majorType;
	
	@JoinColumn(name = "SUB_CODE")
	private SubjectDto subCode;
	
	@JoinColumn(name = "DEPARTMENT_NAME")
	private DepartmentDto departmentName;
	
	@JoinColumn(name = "SUB_TITLE")
	private SubjectDto subTitle;
	
	@JoinColumn(name = "PROFESSOR_NAME")
	private ProfessorDto professorName;
	
	@JoinColumn(name = "LECTURE_PERSONNEL")
	private LectureDto lecturePersonnel;
	
	@JoinColumn(name = "CAPACITY")
	private LectureDto capacity;
}
