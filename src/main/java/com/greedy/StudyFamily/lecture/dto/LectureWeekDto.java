package com.greedy.StudyFamily.lecture.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;


import com.greedy.StudyFamily.admin.dto.FileDto;

import lombok.Data;

@Data
public class LectureWeekDto {

	private Long lectureWeekCode;
	private String week;
	private String fileDivision;
	private LectureDto lecture;
	private Date startDate;
	private Date endDate;
	private FileDto file;
	
	//파일 업로드시 추가로 사용할 로직
	private MultipartFile lectureFiles;

}
