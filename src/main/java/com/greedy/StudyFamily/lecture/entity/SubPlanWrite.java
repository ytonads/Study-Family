package com.greedy.StudyFamily.lecture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
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
@Table(name = "TBL_SUB_PLAN")
@SequenceGenerator(name = "PLAN_SEQ_GENERATOR", sequenceName = "SEQ_PLAN_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class SubPlanWrite {

	@Id
	@Column(name = "PLAN_CODE")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PLAN_SEQ_GENERATOR")
	private Long planCode;
	
	@Column(name = "PURPOSE")
	private String purpose;
	
	@Column(name = "PROFESSOR_CODE")
	private Long professorCode;
	
	@Column(name = "LECTURE_CODE")
	private Long lectureCode;
	
	@Column(name = "PLAN_NAME")
	private String planName;	
	
}
