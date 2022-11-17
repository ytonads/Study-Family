package com.greedy.StudyFamily.lecture.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class LectureWeekDto {

	private Long lectureWeekCode;
	private String week;
	private String fileDivision;
	private LectureDto lectureCode;
	private Date startDate;
	private Date endDate;
	
}
