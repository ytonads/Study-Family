package com.greedy.StudyFamily.calendar.dto;

import java.sql.Date;
import com.greedy.StudyFamily.subject.dto.DepartmentDto;

import lombok.Data;

@Data
public class CalendarDto {
	private Long calendarCode;
	private Date calendarDate;
	private String calendarContent;
	private String calendarStatus;
	private String calendarType;
	private DepartmentDto department;
}