package com.greedy.StudyFamily.admin.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.student.entity.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "TBL_LOGIN")
@DynamicInsert
public class Login implements Serializable{
	
	@Id
	@Column(name="LOGIN_ID")
	private String loginId;
	
	@Column(name="LOGIN_PASSWORD")
	private String loginPassword;
	
	@JoinColumn(name="PROFESSOR_CODE")
	@OneToOne
	private Professor professor;
	
	@JoinColumn(name="STUDENT_NO")
	@OneToOne
	private Student student;
	
	@JoinColumn(name="AUTHORITY_CODE")
	@OneToOne
	private Authority authority;

}
