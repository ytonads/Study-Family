package com.greedy.StudyFamily.admin.dto;

import java.sql.Date;

import org.springframework.web.multipart.MultipartFile;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class FileDto {

	private Long fileCode;
	private String originName;
	private String savedName;
	private String savedRoute;
	private String fileType;
	private String thumbnailRoute;
	private String thumbnailName;
	private Long subnotice;
	private Long schoolNotice;
	private Long taskCode;
	private Long lectureWeekCode;
	
	
	//파일 업로드시 추가로 사용할 로직
	private MultipartFile lectureFiles;
	
	
}
