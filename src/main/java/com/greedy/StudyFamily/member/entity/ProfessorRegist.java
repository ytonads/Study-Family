package com.greedy.StudyFamily.member.entity;

import java.io.Serializable;
import java.sql.Date;

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

import com.greedy.StudyFamily.subject.dto.DepartmentDto;
import com.greedy.StudyFamily.subject.entity.Department;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_PROFESSOR")
@SequenceGenerator(name = "PROFESSOR_SEQ_GENERATOR", sequenceName = "SEQ_PROFESSOR_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class ProfessorRegist implements Serializable {
	
	@Id
	@Column(name="PROFESSOR_CODE")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE, generator = "PROFESSOR_SEQ_GENERATOR"
	)
	private Long professorCode;
	
	@Column(name="PROFESSOR_ID")
	private String professorId;
	
	@Column(name="PROFESSOR_PASSWORD")
	private String professorPassword;
	
	@Column(name="PROFESSOR_NAME")
	private String professorName;
	
	@Column(name="PROFESSOR_POSITION")
	private String professorPosition;
	
	@Column(name="PROFESSOR_HIRE_DATE")
	private Date professorHireDate;
	
	@Column(name="PROFESSOR_REGIST_NUM")
	private String professorRegistNum;
	
	@Column(name="PROFESSOR_PHONE")
	private String professorPhone;
	
	@Column(name="PROFESSOR_ADDRESS")
	private String professorAddress;
	
	@Column(name="PROFESSOR_STATUS")
	private String professorStatus;
	
	@Column(name="PROFESSOR_EMAIL")
	private String professorEmail;
	
	//@Column(name="DEPARTMENT_CODE")
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_CODE")
	private Department department;
	
	@Column(name="MEMBER_ROLE")
	private String memberRole;
	
//	//@Column(name="STUDENT_ID")
//	@ManyToOne
//	@JoinColumn(name="STUDENT_ID")
//	private StudentRegist studentId;
	
}
