package com.greedy.StudyFamily.lecture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.professor.entity.Professor;

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
@DynamicInsert
public class SubPlan {

	@Id
	@Column(name = "PLAN_CODE")
	private Long planCode;
	
	@Column(name = "PURPOSE")
	private String purpose;
	
	@JoinColumn(name = "PROFESSOR_CODE")
	private Professor professor;
	
	@JoinColumn(name = "LECTURE_CODE")
	private Lecture lecture;
	
	@Column(name = "PLAN_NAME")
	private String planName;	
	
}
