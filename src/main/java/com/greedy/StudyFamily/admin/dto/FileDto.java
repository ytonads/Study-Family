package com.greedy.StudyFamily.admin.dto;

import org.springframework.web.multipart.MultipartFile;

import com.greedy.StudyFamily.board.dto.SchoolNoticeDto;
import com.greedy.StudyFamily.board.dto.SubnoticeDto;
import com.greedy.StudyFamily.lecture.dto.LectureWeekDto;
import com.greedy.StudyFamily.lecture.dto.TaskDto;

import lombok.Data;

@Data
public class FileDto {

	private Long fileCode;
	private String originName;
	private String savedName;
	private String savedRoute;
	private String fileType;
	private String thumbnailRoute;
	private String thumbnailName;
	private String fileCategory;
	private SubnoticeDto subnotice;
	private SchoolNoticeDto schoolNotice;
	private TaskDto task;
	private LectureWeekDto lectureWeek;
	
	//파일 업로드시 추가로 사용할 로직
	private MultipartFile lectureFiles;
}
