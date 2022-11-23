package com.greedy.StudyFamily.admin.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.board.entity.SchoolNotice;
import com.greedy.StudyFamily.board.entity.Subnotice;
import com.greedy.StudyFamily.lecture.entity.LectureWeek;
import com.greedy.StudyFamily.lecture.entity.Task;

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
	
	@Column(name="FILE_CATEGORY")
	private String fileCategory;
	
	@Column(name = "START_DATE")
	private Date startDate;
	
	@Column(name = "END_DATE")
	private Date endDate;
	
	@Column(name="SUBNOTICE_CODE")
	private Long subnoticeCode;
	
	@Column(name="SCHOOL_NOTICE_CODE")
	private Long schoolNoticeCode;
	
	@Column(name="TASK_CODE")
	private Long taskCode;
	
	@Column(name="LECTURE_WEEK_CODE")
	private Long lectureWeekCode;
	
	//강좌 수업자료 수정 용도의 메소드 정의
	public void lectureUpdate(Long fileCode, String originName, String savedRoute, Date startDate, Date endDate,
			String fileType, Long lectureWeekCode, String fileCategory) {
		
		this.fileCode = fileCode;
		this.originName = originName;
		this.savedRoute = savedRoute;
		this.startDate = startDate;
		this.endDate = endDate;
		this.fileType = fileType;
		this.lectureWeekCode = lectureWeekCode;
		this.fileCategory = fileCategory;
	}
	
	
	//과제 파일 수정 용도의 메소드 정의
	public void taskUpdate(Long fileCode, String originName, String savedRoute, Long taskCode, String fileType, String fileCategory) {
		
		this.fileCode = fileCode;
		this.originName = originName;
		this.savedRoute = savedRoute;
		this.taskCode = taskCode;
		this.fileType = fileType;
		this.fileCategory = fileCategory;
		
	}
	
	
	

}
