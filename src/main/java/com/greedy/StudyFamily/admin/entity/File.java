package com.greedy.StudyFamily.admin.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@SequenceGenerator(name = "FILE_SEQ_GENERATOR", sequenceName = "SEQ_FILE_CODE", initialValue = 1, allocationSize = 1)
@Table(name="TBL_FILE")
@DynamicInsert
public class File {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "FILE_SEQ_GENERATOR")
	@Column(name="FILE_CODE")
	private Long fileCode;
	
	@Column(name="ORIGIN_NAME")
	private String originName;
	
	@Column(name="SAVED_NAME")
	private String savedName;
	
	@Column(name="SAVED_ROUTE")
	private String savedRoute;

	@Column(name="FILE_TYPE")
	private String fileType;
	
	@Column(name="THUMBNAIL_ROUTE")
	private String thumbnailRoute;
	
	@Column(name="THUMBNAIL_NAME")
	private String thumbnailName;
	
	@Column(name="SUBNOTICE_CODE")
	private Long subnotice;
	
	@Column(name="SCHOOL_NOTICE_CODE")
	private Long schoolNotice;
	
	@Column(name="TASK_CODE")
	private Long task;
	
	@Column(name="LECTURE_WEEK_CODE")
	private Long lectureWeek;
	
	@Column(name="START_DATE")
	private Date startDate;
	
	@Column(name="END_DATE")
	private Date endDate;
	
	
	//강좌 수업자료 수정 용도의 메소드 정의
	public void lectureUpdate(Long fileCode, String originName, String savedRoute,
			String fileType, Long lectureWeek) {
		
		this.fileCode = fileCode;
		this.originName = originName;
		this.savedRoute = savedRoute;
		this.fileType = fileType;
		this.lectureWeek = lectureWeek;
	}
	
	
	//과제 파일 수정 용도의 메소드 정의
	public void taskUpdate(Long fileCode, String originName, String savedRoute, Long task, String fileType) {
		
		this.fileCode = fileCode;
		this.originName = originName;
		this.savedRoute = savedRoute;
		this.task = task;
		this.fileType = fileType;
		
	}
	
	
	

}
