package com.greedy.StudyFamily.admin.service;


import java.util.Arrays;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.admin.repository.AdminRepository;
import com.greedy.StudyFamily.exception.UserNotFoundException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomUserDetailsService implements UserDetailsService {

	
	private final AdminRepository adminRepository;
	private ModelMapper modelMapper;
	
	public CustomUserDetailsService(AdminRepository adminRepository, ModelMapper modelMapper) {
		this.adminRepository = adminRepository;
		this.modelMapper = modelMapper;
	}
	
	
	@Override
	// 아이디 값을 전달 받아서 결과 값을 반환해주는 메서드
	@Transactional
	public UserDetails loadUserByUsername(String loginId) throws UsernameNotFoundException {
		log.info("[CustomUserDetailsService] loadUserByUsername Start ================");
		log.info("[CustomUserDetailsService] memberId : {}", loginId);
		
		return adminRepository.findByLoginId(loginId)
				.map(user -> addAuthorities(user))
				.orElseThrow(() -> new UserNotFoundException(loginId + " 찾을 수 없습니다."));
	}
	
	private LoginDto addAuthorities(Login login) {
		
		LoginDto loginDto = modelMapper.map(login, LoginDto.class);
		loginDto.setAuthorities(Arrays.asList(new SimpleGrantedAuthority(loginDto.getMemberRole())));
		
		return loginDto;
	}

 }
