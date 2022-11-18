package com.greedy.StudyFamily.board.entity;

import java.sql.Date;

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

import com.greedy.StudyFamily.lecture.entity.Lecture;

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
@SequenceGenerator(name = "SUBNOTICE_SEQ_GENERATOR", sequenceName = "SEQ_SUBNOTICE_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Subnotice {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SUBNOTICE_SEQ_GENERATOR")
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
	
	@ManyToOne
	@JoinColumn(name = "LECTURE_CODE")
	private Lecture lecture;
	
}
