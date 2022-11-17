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

import com.greedy.StudyFamily.student.entity.Department;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_SCHOOL_NOTICE")
@SequenceGenerator(name = "SCHOOL_NOTICE_SEQ_GENERATOR", sequenceName = "SEQ_SCHOOL_NOTICE_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class SchoolNotice {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "SCHOOL_NOTICE_SEQ_GENERATOR")
	@Column(name = "SCHOOL_NOTICE_CODE")
	private Long schoolNoticeCode;
	
	@Column(name = "SCHOOL_NOTICE_TITLE")
	private String schoolNoticeTitle;
	
	@Column(name = "SCHOOL_NOTICE_CONTENT")
	private String schoolNoticeContent;
	
	@Column(name = "SCHOOL_NOTICE_REG_DATE")
	private Date schoolNoticeRegDate;
	
	@Column(name = "SCHOOL_NOTICE_UPD_DATE")
	private Date schoolNoticeUpdDate;
	
	@Column(name = "SCHOOL_NOTICE_DEL_DATE")
	private Date schoolNoticeDelDate;
	
	@Column(name = "SCHOOL_NOTICE_STATE")
	private String schoolNoticeState;
	
	@Column(name = "SCHOOL_NOTICE_CATEGORY")
	private String schoolNoticeCategory;
	
	@ManyToOne
	@JoinColumn(name = "DEPARTMENT_CODE")
	private Department departmentCode;

}
