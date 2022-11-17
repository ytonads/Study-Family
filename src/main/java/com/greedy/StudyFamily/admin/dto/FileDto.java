package com.greedy.StudyFamily.admin.dto;

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
	private SubnoticeDto subnoticeCode;
	private SchoolNoticeDto schoolNoticeCode;
	private TaskDto taskCode;
	private LectureWeekDto lectureWeekCode;
	
}
