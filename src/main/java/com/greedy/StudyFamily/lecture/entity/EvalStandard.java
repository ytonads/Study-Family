package com.greedy.StudyFamily.lecture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_EVAL_STANDARD")
@DynamicInsert
public class EvalStandard {

	@Id
	@Column(name = "EVAL_STANDARD_CODE")
	private Long evalStandardCode;
	
	@Column(name = "WIGHTED")
	private Long weighted;
	
	@Column(name = "EVAL_STANDARD")
	private String evalStandard;
	
	@ManyToOne
	@JoinColumn(name = "LECTURE_CODE")
	private Lecture lectureCode;
	
}
