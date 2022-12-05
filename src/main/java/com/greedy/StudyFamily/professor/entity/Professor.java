package com.greedy.StudyFamily.professor.entity;

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
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name = "PROFESSOR_SEQ_GENERATOR", sequenceName = "SEQ_PROFESSOR_CODE", initialValue = 1, allocationSize = 1)
@Table(name="TBL_PROFESSOR")
@DynamicInsert	// 기본 역할에 대한 설정
public class Professor {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "PROFESSOR_SEQ_GENERATOR")
	@Column(name="PROFESSOR_CODE")
	private Long professorCode;
	
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
	
	@ManyToOne
	@JoinColumn(name="DEPARTMENT_CODE")
	private Department department;

	

	
	/* 교수정보 수정 용도 메소드 정의 */
	public void update(String professorName, String professorPosition, String professorHireDate,
			String professorRegistNum, String professorPhone, String professorAddress, String professorStatus,
			String professorEmail, DepartmentDto department) {
		
		this.professorName = professorName;
		this.professorPosition = professorPosition;
		this.professorRegistNum = professorRegistNum;
		this.professorPhone = professorPhone;
		this.professorAddress = professorAddress;
		this.professorStatus = professorStatus;
		this.professorEmail = professorEmail;
		
		
	}


}







