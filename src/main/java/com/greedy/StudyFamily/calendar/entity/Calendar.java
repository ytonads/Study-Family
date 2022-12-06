package com.greedy.StudyFamily.calendar.entity;

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

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TBL_CALENDAR")
@DynamicInsert	// 기본 역할에 대한 설정
public class Calendar {

	@Id
	@Column(name="CALENDAR_CODE")
	private Long calendarCode;
	
	@Column(name="CALENDAR_DATE")
	private Date calendarDate;
	
	@Column(name="CALENDAR_CONTENT")
	private String calendarContent;
	
	@Column(name="CALENDAR_STATUS")
	private String calendarStatus;
	
	@Column(name="CALENDAR_TYPE ")
	private String calendarType;
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_CODE")
	private Department department;
	
	public void update(String calendarContent) {
		this.calendarContent = calendarContent;
	}
		
}
