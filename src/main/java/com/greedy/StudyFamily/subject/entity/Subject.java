package com.greedy.StudyFamily.subject.entity;

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

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TBL_SUBJECT")
@DynamicInsert
public class Subject {
	
	@Id
	@Column(name="SUB_CODE")
	private Long subCode;
	
	@Column(name="SUB_TITLE")
	private String subTitle;
	
	@Column(name="MAJOR_TYPE")
	private String majorType;
	
	@Column(name="SUB_GRADE")
	private Long subGrade;
	
	@JoinColumn(name="DEPARTMENT_CODE")
	@ManyToOne
	private Department department;   
	
	
	
}