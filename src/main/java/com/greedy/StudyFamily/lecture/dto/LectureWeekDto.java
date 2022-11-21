package com.greedy.StudyFamily.lecture.dto;

import java.sql.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.greedy.StudyFamily.admin.dto.FileDto;
import com.greedy.StudyFamily.admin.entity.File;

import lombok.Data;

@Data
public class LectureWeekDto {

	private Long lectureWeekCode;
	private String week;
	private String fileDivision;
	private LectureDto lectures;
	private Date startDate;
	private Date endDate;
//	private FileDto file;
	
//	private List<File> Files;

}
