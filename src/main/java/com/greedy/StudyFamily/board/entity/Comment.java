package com.greedy.StudyFamily.board.entity;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import com.greedy.StudyFamily.professor.entity.Professor;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Entity
@Table(name = "TBL_COMMENT")
@DynamicInsert
public class Comment {

	@Id
	@Column(name = "COMMENT_CODE")	
	private Long commentCode;
	
	@Column(name = "COMMENT_CONTENT")
	private String commentContent;
	
	@Column(name = "REGISTRATION_DATE")
	private Date registrationDate;
	
	@Column(name = "DELETE_DATE")
	private Date deleteDate;
	
	@Column(name = "MODIFIED_DATE")
	private Date modifiedDate;
	
	@Column(name = "COMMENT_STATE")
	private String commentState;
	
	@OneToOne
	@JoinColumn(name = "QA_CODE")
	private QaBoard qaCode;
	
	@ManyToOne
	@JoinColumn(name = "PROFESSOR_CODE")
	private Professor professor;
	
	
}
