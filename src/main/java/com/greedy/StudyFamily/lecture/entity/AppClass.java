package com.greedy.StudyFamily.lecture.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name = "TBL_APP_CLASS")
@DynamicInsert
public class AppClass implements Serializable {

	@Id
	@ManyToOne
	@JoinColumn(name = "STUDENT_NO")
	private Student student;
	
	@Id
	@ManyToOne
	@JoinColumn(name = "LECTURE_CODE")
	private Lecture lecture;
	
}
