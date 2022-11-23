package com.greedy.StudyFamily.admin.entity;

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
@Table(name="TBL_AUTHORITY")
@DynamicInsert
public class Authority {
	
//	@Id
//	@Column(name="AUTHORITY_CODE")
//	private Long authorityCode;

	@Id
	@Column(name="AUTHORITY_NAME")
	private String authorityName;
	
	@Column(name="AUTHORITY_EXPLAIN")
	private String authorityExplain;

}
