package com.greedy.StudyFamily.professor.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.professor.dto.ProfessorPositionDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
	
	@Column(name = "PROFESSOR_CODE")
	private ProfessorDto professorCode;
	
	@ManyToOne
	@JoinColumn(name = "PROFESSOR_POSITION")
	private ProfessorPosition professorPosition;

}
