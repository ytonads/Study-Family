package com.greedy.StudyFamily.admin.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
public class FileDto {

	private Long fileCode;
	private String originName;
	private String savedName;
	private String savedRoute;
	private String fileType;
	private String thumbnailRoute;
	private String thumbnailName;
	private Long subnotice;
	private Long school;
	private Long task;
	private Long lectureWeek;
	private Date startDate;
	private Date endDate;
	
	
	//파일 업로드시 추가로 사용할 로직
	@JsonIgnore
	private MultipartFile lectureFiles;
	
	
}
