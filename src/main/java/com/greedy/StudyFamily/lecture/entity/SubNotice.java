package com.greedy.StudyFamily.lecture.entity;

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

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_SUBNOTICE")
@DynamicInsert
public class SubNotice {

	@Id
	@Column(name = "SUBNOTICE_CODE")
	private Long subnoticeCode;
	
	@Column(name = "SUBNOTICE_TITLE")
	private String subnoticeTitle;
	
	@Column(name = "CONTENT")
	private String content;
	
	@Column(name = "REGISTRATION_DATE")
	private Date registrationDate;
	
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "DELETE_DATE")
	private Date deleteDate;
	
	@Column(name = "STATUS")
	private String status;
	
	@ManyToOne
	@JoinColumn(name = "LECTURE_CODE")
	private Lecture lectureCode;
}
