package com.greedy.StudyFamily.student.entity;

import java.util.ArrayList;
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
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "STUDENT_SEQ_GENERATOR")
	@Column(name = "STUDENT_CODE")
	private String studentCode;
	
	// 오타 수정
	@Column(name = "STUDENT_NAME")
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
	
	/* 추가 */
	@ManyToOne
	@JoinColumn(name = "SCHOOL_STATUS_CODE")
	private SchoolStatus schoolStatus;

	@OneToMany(mappedBy = "student")
	private List<AppClass> appClasses = new ArrayList<>();
	 
    //== 수강 취소 ==//
    public void cancel(AppClass appClass) {
        this.appClasses.remove(appClass);
    }


    /* 학생정보 수정 용도 메소드 정의 */
	public void update(Long studentNo, String studentName, String admissionsDay, Department department, String studentRegistNum,
			String grade, String gender, String studentEmail, String studentPhone, String studentAddress, String nationality, SchoolStatus schoolStatus) {

		this.studentNo = studentNo;
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
		this.schoolStatus = schoolStatus;
	
	}

	/* 학생 마이페이지 개인정보 수정 메소드 */
	public void update2(Long studentNo, String studentPhone, String studentAddress, String studentEmail) {

		this.studentNo = studentNo;
		this.studentPhone = studentPhone;
		this.studentAddress = studentAddress;
		this.studentEmail = studentEmail;
	
	}


	
}