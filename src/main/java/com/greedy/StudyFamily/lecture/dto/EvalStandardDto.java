package com.greedy.StudyFamily.lecture.dto;

import lombok.Data;

@Data
public class EvalStandardDto {

	private Long evalStandardCode;
	private Long weighted;
	private String evalStandard;
	private LectureDto lecture;
	
}
