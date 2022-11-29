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

import com.greedy.StudyFamily.professor.entity.Professor;
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
@Table(name = "TBL_STUDENT")
@SequenceGenerator(name = "STUDENT_SEQ_GENERATOR", sequenceName = "SEQ_STUDENT_NO", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class StudentRegist implements Serializable {
	
	@Id
	@Column(name="STUDENT_NO")
	@GeneratedValue(
			strategy = GenerationType.SEQUENCE, generator = "STUDENT_SEQ_GENERATOR"
	)
	private Long studentNo;
	
	@Column(name="STUDENT_CODE")
	private String studentCode;
	
	@Column(name="STUDENT_NAME")
	private String studentName;
	
	@Column(name="ADMISSIONS_DAY")
	private String admissionsDay;
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_CODE")
	private Department department;
	
	@Column(name = "STUDENT_REGIST_NUM")
	private String studentRegistNum;
	
	@Column(name = "GRADE")
	private String grade;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "STUDENT_EMAIL")
	private String studentEmail;
	
	@Column(name = "STUDENT_PHONE")
	private String studentPhone;
	
	@Column(name = "STUDENT_ADDRESS")
	private String studentAddress;
	
	@Column(name = "NATIONALITY")
	private String nationality;
	
//	@ManyToOne
//	@JoinColumn(name = "PROFESSOR_ID")
//	private Professor professorId;
	
}
