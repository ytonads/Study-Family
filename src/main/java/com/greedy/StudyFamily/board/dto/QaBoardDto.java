package com.greedy.StudyFamily.board.dto;

import java.sql.Date;

import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Data;

@Data
public class QaBoardDto {

	private Long qaCode;
	private String qaTitle;
	private String qaContent;
	private Date qaRegisdate;
	private Date qaModidate;
	private Date qaDeledate;
	private String qaStatus;
	private LectureDto lecture;
	private StudentDto student;
	
}
