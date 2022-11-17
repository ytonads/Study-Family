package com.greedy.StudyFamily.subject.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TBL_DEPARTMENT")
@SequenceGenerator(name="SEQ_DEPARTMENT_GENERATOR", sequenceName="SEQ_DEPARTMENT_CODE", 
					allocationSize=1)
public class Department {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="SEQ_DEPARTMENT_GENERATOR")
	@Column(name="DEPARTMENT_CODE")
	private Long DepartmentCode;
	
	@Column(name="DEPARTMENT_NAME")
	private String DepartmentName;
	
	@Column(name="DEPARTMENT_ABOLISH")
	private String DepartmentAbolish;
	
}
