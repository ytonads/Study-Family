package com.greedy.StudyFamily.student.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.student.dto.SchoolStatusDto;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_STUDENT_HISTORY")
@DynamicInsert
public class StudentHistory {
	
	@Id
	@Column(name = "STUDENT_MODIFY_CODE")
	private Long studentModifyCode;
	
	@Column(name = "STUDENT_MODIFY_DATE")
	private Date studentModifyDate;

	@ManyToOne
	@JoinColumn(name = "STUDENT_NO")
	private Student studentNo;
	
	@ManyToOne
	@JoinColumn(name = "SCHOOL_STATUS_CODE")
	private SchoolStatus schoolStatus;
	

}
