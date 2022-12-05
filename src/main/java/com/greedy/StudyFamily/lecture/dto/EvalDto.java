package com.greedy.StudyFamily.lecture.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvalDto {

	private Long evalCode;
	private String evalGrade;
	private Long evalResult;
	private Long evalMiddle;
	private Long evalFinal;
	private Long evalTask;
	private Long evalAttend;
	private LectureDto lecture;
	private AppClassDto appClass;
	
}
