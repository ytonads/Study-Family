package com.greedy.StudyFamily.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.student.entity.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_LOGIN")
@SequenceGenerator(name = "LOGIN_SEQ_GENERATOR", sequenceName = "SEQ_LOGIN_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Login implements Serializable{
	
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "LOGIN_SEQ_GENERATOR")
	@Id
	@Column(name="LOGIN_CODE")
	private Long loginCode;
	
	@Column(name="LOGIN_ID")
	private String loginId;
	
	@Column(name="LOGIN_PASSWORD")
	private String loginPassword;
	
	// 테스트
	@JoinColumn(name="PROFESSOR_CODE")
	@OneToOne
	private Professor professor;
	
	@JoinColumn(name="STUDENT_NO")
	@OneToOne
	private Student student;
	
	@JoinColumn(name="MEMBER_ROLE")
	private String memberRole;

}
