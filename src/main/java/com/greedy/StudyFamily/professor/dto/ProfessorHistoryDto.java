package com.greedy.StudyFamily.professor.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ProfessorHistoryDto {

	private Long professorModifyCode;
	private Date professorModifyDate;
	private ProfessorDto professor;
	private ProfessorPositionDto professorPosition;
	
}
