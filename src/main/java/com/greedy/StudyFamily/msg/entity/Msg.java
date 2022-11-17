package com.greedy.StudyFamily.msg.entity;

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

import com.greedy.StudyFamily.admin.entity.Login;
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
@Table(name = "TBL_MSG")
@SequenceGenerator(name = "MSG_SEQ_GENERATOR", sequenceName = "SEQ_MSG_CODE", initialValue = 1, allocationSize = 1)
@DynamicInsert
public class Msg {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "MSG_SEQ_GENERATOR")
	@Column(name = "MSG_CODE")
	private Long msgCode;
	
	@Column(name = "SHIP_DATE")
	private Date shipDate;
	
	@Column(name = "RECEIVE_DATE")
	private Date receiveDate;
	
	@Column(name = "MSG_TITLE")
	private String msgTitle;
	
	@Column(name = "MSG_CONTENT")
	private String msgContent;
	
	@ManyToOne
	@JoinColumn(name = "LECTURE_CODE")
	private Lecture lectureCode;
	
	@Column(name = "MSG_STATUS")
	private String msgStatus;
	
	@Column(name = "DELETE_STATUS")
	private String deleteStatus;
	
	@ManyToOne
	@JoinColumn(name = "LOGIN_ID")
	private Login sender;
	
	@ManyToOne
	@JoinColumn(name = "LOGIN_ID")
	private Login receiver;
	
}
