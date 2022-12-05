package com.greedy.StudyFamily.lecture.entity;

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
@Table(name = "TBL_APP_CLASS")
@SequenceGenerator(name = "APP_SEQ_CLASS", sequenceName = "SEQ_APP_CLASS_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class AppClass {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "APP_SEQ_CLASS")
	@Column(name = "APP_CLASS_CODE")
	private Long appClassCode;
	
	@ManyToOne
	@JoinColumn(name = "STUDENT_NO")
	private Student student;
	
	@ManyToOne
	@JoinColumn(name = "LECTURE_CODE")
	private Lecture lecture;

	public void insertEval(Long evalCode, String evalGrade, Long evalResult, Long evalMiddle, Long evalFinal,
			Long evalTask, Long evalAttend) {
		
		
	}

//	private Long evalCode;
//	private String evalGrade;
//	private Long evalResult;
//	private Long evalMiddle;
//	private Long evalFinal;
//	private Long evalTask;
//	private Long evalAttend;
//
//	public void insertEval(Long evalCode, String evalGrade, Long evalResult, Long evalMiddle, Long evalFinal,
//			Long evalTask, Long evalAttend) {
//		
//		this.evalCode = evalCode;
//		this.evalGrade = evalGrade;
//		this.evalResult = evalResult;
//		this.evalMiddle = evalMiddle;
//		this.evalFinal = evalFinal;
//		this.evalTask = evalTask;
//		this.evalAttend = evalAttend;
//		
//	}
	
	
}
