package com.greedy.StudyFamily.lecture.dto;

import java.sql.Date;

import com.greedy.StudyFamily.professor.dto.ProfessorDto;

import lombok.Data;

@Data
public class CommentDto {

	private Long commentCode;
	private String commentContent;
	private Date registrationDate;
	private Date deleteDate;
	private Date modifiedDate;
	private String commentState;
	private QaBoardDto qaCode;
	private ProfessorDto professorCode;
	
}
