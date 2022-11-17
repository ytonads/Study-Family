package com.greedy.StudyFamily.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.board.entity.SchoolNotice;
import com.greedy.StudyFamily.lecture.entity.LectureWeek;
import com.greedy.StudyFamily.lecture.entity.Task;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TBL_FILE")
@DynamicInsert
public class File {
	
	@Id
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
	
	@JoinColumn(name="SUBNOTICE_CODE")
	private int subnoticeCode;
	
	@JoinColumn(name="SCHOOL_NOTICE_CODE")
	private SchoolNotice schoolNoticeCode;
	
	@JoinColumn(name="TASK_CODE")
	private Task taskCode;
	
	@JoinColumn(name="LECTURE_WEEK_CODE")
	private LectureWeek lectureWeekCode;

}
