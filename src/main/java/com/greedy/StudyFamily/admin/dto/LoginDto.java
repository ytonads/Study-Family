package com.greedy.StudyFamily.admin.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import org.springframework.security.core.userdetails.UserDetails;

import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Data;

@Data
public class LoginDto implements UserDetails {

	private Long loginCode;
	private String loginId;
	private String loginPassword;
	private String memberRole;
	private ProfessorDto professor;
	private StudentDto student;
	
	// 이하 코드 security 인증, 인가와 관련 된 코드
	private Collection<? extends GrantedAuthority> authorities;

	@Override
	public String getPassword() {
		return loginPassword;
	}

	@Override
	public String getUsername() {
		return loginId;
	}

	@Override
	public boolean isAccountNonExpired() {
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return false;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}
	
	
	
}
