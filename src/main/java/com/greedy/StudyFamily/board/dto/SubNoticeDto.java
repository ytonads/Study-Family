package com.greedy.StudyFamily.board.dto;

import java.sql.Date;

import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;

import lombok.Data;

@Data
public class SubNoticeDto {

	private Long subnoticeCode;
	private String subnoticeTitle;
	private String content;
	private Date registrationDate;
	private Date modifiedDate;
	private Date deleteDate;
	private String status;
	private LectureDto lecture;
	private ProfessorDto professor;
}
