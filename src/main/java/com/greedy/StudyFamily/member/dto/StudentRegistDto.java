package com.greedy.StudyFamily.member.dto;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.greedy.StudyFamily.subject.dto.DepartmentDto;

import lombok.Data;

@Data
public class StudentRegistDto implements UserDetails {

	private Long studentNo;
	private String studentCode;
	private String studentId;
	private String studentPassword;
	private String studentName;
	private DepartmentDto department;
	private String admissionsDay;
	private String studentRegistNum;
	private String grade;
	private String gender;
	private String studentEmail;
	private String studentPhone;
	private String studentAddress;
	private String nationality;
	private String memberRole;
	
//	public StudentRegistDto() {
//		this.memberRole = "ROLE_STUDENT";
//	}
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return studentPassword;
	}

	@Override
	public String getUsername() {
		return studentId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}
	
}
