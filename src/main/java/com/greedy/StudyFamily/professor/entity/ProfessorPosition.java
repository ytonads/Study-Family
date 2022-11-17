package com.greedy.StudyFamily.professor.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TBL_PROFESSOR_POSITION")
@DynamicInsert
public class ProfessorPosition {
	
	@Id
	@Column(name = "PROFESSOR_POSITION_CODE")
	private Long professorPositionCode;
	
	@Column(name = "PROFESSOR_POSITION_NAME")
	private String professorPositionName;

}
