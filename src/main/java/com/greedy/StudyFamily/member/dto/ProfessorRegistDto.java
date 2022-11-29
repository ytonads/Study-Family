package com.greedy.StudyFamily.member.dto;

import java.sql.Date;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.greedy.StudyFamily.subject.dto.DepartmentDto;

import lombok.Data;

@Data
public class ProfessorRegistDto implements UserDetails {

	private Long professorCode;
	private String professorId;
	private String professorPassword;
	private String professorName;
	private String professorPosition;
	private String professorHireDate;
	private String professorRegistNum;
	private String professorPhone;
	private String professorAddress;
	private String professorStatus;
	private String professorEmail;
	private DepartmentDto department;
	private String memberRole;
	
//	public ProfessorRegistDto() {
//		this.memberRole = "ROLE_PROFESSOR";
//	}

	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}

	@Override
	public String getPassword() {
		return professorPassword;
	}

	@Override
	public String getUsername() {
		return professorId;
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
