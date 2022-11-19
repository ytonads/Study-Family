package com.greedy.StudyFamily.professor.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TBL_PROFESSOR_HISTORY")
@DynamicInsert
public class ProfessorHistory {
	
	@Id
	@Column(name= "PROFESSOR_MODIFY_CODE")
	private Long professorModifyCode;
	
	@Column(name = "PROFESSOR_MODIFY_DATE")
	private Date professorModifyDate;
	
	@ManyToOne
	@JoinColumn(name = "PROFESSOR_CODE")
	private Professor professor;
	
	@ManyToOne
	@JoinColumn(name = "PROFESSOR_POSITION_CODE")
	private ProfessorPosition professorPosition;

}
