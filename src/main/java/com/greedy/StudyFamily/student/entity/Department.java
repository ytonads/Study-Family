package com.greedy.StudyFamily.student.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity	
@DynamicInsert
@SequenceGenerator(name = "DEPARTMENT_SEQ_GENERATOR", sequenceName = "SEQ_DEPARTMENT_CODE", initialValue = 1, allocationSize = 1)
@Table(name = "TBL_PRODUCT")
public class Department {

	@Id
	@Column(name = "DEPARTMENT_CODE")
	private Long departmentCode;
	
	@Column(name = "DEPARTMENT_NAME")
	private String departmentName;
	
	@Column(name = "DEPARTMENT_ABOLISH")
	private String departmentAbolish;
	
}
