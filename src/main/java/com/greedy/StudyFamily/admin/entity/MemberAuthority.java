package com.greedy.StudyFamily.admin.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name="TBL_MEMBER_AUTHORITY")
@DynamicInsert
public class MemberAuthority {
	
	@Id
	@Column(name="LOGIN_ID")
	private String loginId;
	
	@Id
	@Column(name="AUTHORITY_CODE")
	private Long authorityCode;

}
