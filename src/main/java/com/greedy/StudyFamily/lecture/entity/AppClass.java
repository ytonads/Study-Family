package com.greedy.StudyFamily.lecture.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.student.entity.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_APPCLASS")
@DynamicInsert
public class AppClass {

	@ManyToOne
	@JoinColumn(name = "STUDENT_CODE")
	private Student studentCode;
	
	@ManyToOne
	@JoinColumn(name = "LECTURE_CODE")
	private Lecture lectureCode;
	
}
