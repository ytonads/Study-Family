package com.greedy.StudyFamily.student.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity	
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
