package com.greedy.StudyFamily.board.dto;

import java.sql.Date;

import com.greedy.StudyFamily.board.dto.QaBoardDto;
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
	private QaBoardDto qaBoard;
	private ProfessorDto professor;
	
}
