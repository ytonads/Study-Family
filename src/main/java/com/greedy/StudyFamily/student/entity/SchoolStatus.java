package com.greedy.StudyFamily.student.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "TBL_SCHOOL_STATUS")
@DynamicInsert
public class SchoolStatus {
	
	@Id
	@Column(name = "SCHOOL_STATUS_CODE")
	private Long schoolStatusCode;
	
	@Column(name = "SCHOOL_STATUS_NAME")
	private String schoolStatusName;

}
