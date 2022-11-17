package com.greedy.StudyFamily.lecture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_EVAL")
@DynamicInsert
public class Eval {

	@ManyToOne
	@JoinColumn(name = "EVAL_STANDARD_CODE")
	private EvalStandard evalStandardCode;
	
	@ManyToOne
	@JoinColumn(name = "STUDENT_No")
	private StudentDto studentNo;
	
	@ManyToOne
	@JoinColumn(name = "LECTURE_CODE")
	private LectureDto lectureCode;
	
	@Column(name = "EVAL_GRADE")
	private Long evalGrade;
	
}
