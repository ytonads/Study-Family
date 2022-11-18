package com.greedy.StudyFamily.lecture.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class LectureWeekDto {

	private Long lectureWeekCode;
	private String week;
	private String fileDivision;
	private LectureDto lectureCode;
	private Date startDate;
	private Date endDate;
	
	//파일 업로드시 추가로 사용할 로직
	private MultipartFile lectureFiles;
	
}
