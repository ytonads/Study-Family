package com.greedy.StudyFamily.lecture.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name = "TBL_COURSE_HISTORY")
@SequenceGenerator(name = "COURSE_HISTORY_SEQ_GENERATOR", sequenceName = "SEQ_COURSE_HISTORY", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class CourseHistory {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "COURSE_HISTORY_SEQ_GENERATOR")
	@Column(name = "COURSE_CODE")
	private Long courseCode;
	
	@ManyToOne
	@JoinColumn(name = "STUDENT_NO")
	private Student student;
	
	@Column(name = "COURSE_TIME")
	private Long courseTime;
	
	@ManyToOne
	@JoinColumn(name = "LECTURE_WEEK_CODE")
	private LectureWeek lectureWeek;
	
	@Column(name = "COURSE_STATUS")
	private String courseStatus;	
	
	
	public void historyUpdate(Long courseCode, Long courseTime, String courseStatus) {
		
		this.courseCode = courseCode;
		this.courseTime = courseTime;
		this.courseStatus = courseStatus;
	}
	
	
}
