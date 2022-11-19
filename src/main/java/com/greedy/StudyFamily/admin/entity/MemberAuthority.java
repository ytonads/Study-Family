package com.greedy.StudyFamily.admin.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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
@Table(name="TBL_MEMBER_AUTHORITY")
@DynamicInsert
public class MemberAuthority implements Serializable {
	
	@Id
	@ManyToOne
	@JoinColumn(name="LOGIN_CODE")
	private Login login;
	
	@Id
	@ManyToOne
	@JoinColumn(name="AUTHORITY_CODE")
	private Authority authority;

}
