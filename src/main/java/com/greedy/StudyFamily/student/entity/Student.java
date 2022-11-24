package com.greedy.StudyFamily.student.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.subject.entity.Department;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name = "STUDENT_SEQ_GENERATOR", sequenceName = "SEQ_STUDENT_NO", initialValue = 1, allocationSize = 1)
@DynamicInsert
@Table(name = "TBL_STUDENT")
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_SEQ_GENERATOR")
	@Column(name = "STUDENT_NO")
	private Long studentNo;
	
	@Column(name = "STUDENT_CODE")
	private String studentCode;
	
	@Column(name = "Student_Name")
	private String studentName;
	
	@Column(name = "ADMISSIONS_DAY")
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

	@OneToMany(mappedBy = "student")
	//@JoinColumn(name = "APP_CLASS_CODE")
	 private List<AppClass> appClasses;
	 


	/* 학생정보 수정 용도 메소드 정의 */
	public void update(String studentCode, String studentName, String admissionsDay, Department department, String studentRegistNum,
			String grade, String gender, String studentEmail, String studentPhone, String studentAddress, String nationality) {

		this.studentCode = studentCode;
		this.studentName = studentName;
		this.admissionsDay = admissionsDay;
		this.department = department;
		this.studentRegistNum = studentRegistNum;
		this.grade = grade;
		this.gender = gender;
		this.studentEmail = studentEmail;
		this.studentPhone = studentPhone;
		this.studentAddress = studentAddress;
		this.nationality = nationality;
	
}


	
}