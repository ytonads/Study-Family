package com.greedy.StudyFamily.lecture.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.student.entity.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@SequenceGenerator(name = "EVAL_SEQ_GENERATOR", sequenceName = "SEQ_EVAL_CODE", initialValue = 1, allocationSize = 1)
@Table(name = "TBL_EVAL")
@DynamicInsert
public class Eval {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "EVAL_SEQ_GENERATOR")
	@Column(name = "EVAL_CODE")
	private Long evalCode;
	
	@JoinColumn(name = "APP_CLASS_CODE")
	@ManyToOne
	private AppClass appClass;
	
	@Column(name = "EVAL_GRADE")
	private String evalGrade;
	
	@Column(name = "EVAL_RESULT")
	private Long evalResult;
	
	@Column(name = "EVAL_MIDDLE")
	private Long evalMiddle;
	
	@Column(name = "EVAL_FINAL")
	private Long evalFinal;
	
	@Column(name = "EVAL_TASK")
	private Long evalTask;
	
	@Column(name = "EVAL_ATTEND")
	private Long evalAttend;
	
	@JoinColumn(name = "LECTURE_CODE")
	@ManyToOne
	private Lecture lecture;
	
}
