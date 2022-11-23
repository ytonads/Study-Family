package com.greedy.StudyFamily.admin.dto;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Data;

@Data
public class LoginDto implements UserDetails {

	// 엔티티에서 조회해온 정보를 저장 하는 값
	
	//private Long loginCode;
	private String loginId;
	private String loginPassword;
	private String memberRole;
	private ProfessorDto professor;
	private StudentDto student;

	// security 인증, 인가 코드
	
	private Collection<? extends GrantedAuthority> authorities;
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return authorities;
	}
	@Override
	public String getPassword() {
		return loginPassword;
	}
	@Override
	public String getUsername() {
		return loginId;
	}
	
	// 이하 계정이 만료되지 않음, 잠기지 않음, 인가가 만료되지 않음, 사용가능임을 true로 설정
	
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
