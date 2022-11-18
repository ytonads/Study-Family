package com.greedy.StudyFamily.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.subject.entity.Department;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_CALENDER")
@DynamicInsert
public class Calender {

	@Id
	@Column(name = "CALENDER_CODE")
	private Long calenderCode;
	
	@Column(name = "CALENDER_DATE")
	private Date calenderDate;
	
	@Column(name = "CALENDER_CONTENT")
	private String calenderContent;
	
	@Column(name = "CALENDER_STATUS")
	private String calenderStatus;
	
	@Column(name = "CALENDER_TYPE")
	private String calenderType;
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_CODE")
	private Department department;
	
}
