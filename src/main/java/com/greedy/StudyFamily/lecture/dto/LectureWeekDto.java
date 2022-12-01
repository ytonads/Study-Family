package com.greedy.StudyFamily.lecture.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greedy.StudyFamily.admin.dto.FileDto;
import com.greedy.StudyFamily.admin.entity.File;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LectureWeekDto {

	private Long lectureWeekCode;
	private String week;
	private String fileDivision;
	private LectureDto lectures;
	private List<File> file;
	private List<CourseHistoryDto> courseHistories;

	
	
	
	
}
