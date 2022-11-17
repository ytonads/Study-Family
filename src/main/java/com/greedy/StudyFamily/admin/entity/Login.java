package com.greedy.StudyFamily.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.student.entity.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_LOGIN")
@DynamicInsert
public class Login {
	
	@Id
	@Column(name="LOGIN_ID")
	private String loginId;
	
	@Column(name="LOGIN_PASSWORD")
	private String loginPassowrd;
	
	@JoinColumn(name="PROFESSOR_CODE")
	@OneToOne
	private int professorCode;
	
	@JoinColumn(name="STUDENT_NO")
	@OneToOne
	private Student studentNo;
}
