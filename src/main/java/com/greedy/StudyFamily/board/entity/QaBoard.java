package com.greedy.StudyFamily.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.student.entity.Student;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_QABOARD")
@DynamicInsert
public class QaBoard {

	@Id
	@Column(name = "QA_CODE")
	private Long qaCode;
	
	@Column(name = "QA_TITLE")
	private String qaTitle;
	
	@Column(name = "QA_CONTENT")
	private String qaContent;
	
	@Column(name = "QA_RESGIST_DATE")
	private Date qaRegisdate;
	
	@Column(name = "QA_MODI_DATE")
	private Date qaModidate;
	
	@Column(name = "QA_DELETE_DATE")
	private Date qaDeledate;
	
	@Column(name = "QA_STATUS")
	private String qaStatus;
	
	@ManyToOne
	@JoinColumn(name = "LECTURE_CODE")
	private LectureDto lectureCode;
	
	@ManyToOne
	@JoinColumn(name = "STUDENT_NO")
	private Student studentNo;
	
}
