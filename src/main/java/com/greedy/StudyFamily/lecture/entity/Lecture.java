package com.greedy.StudyFamily.lecture.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.subject.entity.Subject;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_LECTURE")
@DynamicInsert
public class Lecture {
	
	@Id
	@Column(name = "LECTURE_CODE")
	private Long lectureCode;
	
	@ManyToOne
	@JoinColumn(name = "SUB_CODE")
	private Subject subject;
	
	@Column(name = "CAPACITY")
	private Long capacity;

	@ManyToOne
	@JoinColumn(name = "PROFESSOR_CODE")
	private Professor professor;
	
	@Column(name = "LECTURE_NAME")
	private String lectureName;
	
	@Column(name = "LECTURE_PERSONNEL")
	private Long lecturePersonnel;
	
	@Column(name = "OPENING_DATE")
	private String openingDate;
	
	
	

}