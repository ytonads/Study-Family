package com.greedy.StudyFamily.lecture.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.admin.entity.File;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_LECTURE_WEEK")
@DynamicInsert
public class LectureWeek {

	@Id
	@Column(name = "LECTURE_WEEK_CODE")
	private Long lectureWeekCode;
	
	@Column(name = "WEEK")
	private String week;
	
	@Column(name = "FILE_DIVISION")
	private String fileDivision;
	
	@ManyToOne
	@JoinColumn(name = "LECTURE_CODE")
	private Lecture lectures;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	
	
}
