package com.greedy.StudyFamily.admin.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.dto.TokenDto;
import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.admin.repository.AdminRepository;
import com.greedy.StudyFamily.exception.LoginFailedException;
import com.greedy.StudyFamily.exception.UserNotFoundException;
import com.greedy.StudyFamily.jwt.TokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class MemberService {

	private final AdminRepository adminRepository;
	private final ModelMapper modelMapper;
	
	public MemberService(AdminRepository adminRepository, ModelMapper modelMapper) {
		this.adminRepository = adminRepository;
		this.modelMapper = modelMapper;
	}
	
	/* 3. 회원 정보 조회 */
	@Transactional
	public LoginDto selectMyInfo(String loginId) {
		
		log.info("[MemberService] selectMyInfo Start ====================");
		log.info("[MemberService] loginId : {}", loginId);
		
		Login login = adminRepository.findByLoginId(loginId)
				.orElseThrow(() -> new UserNotFoundException(loginId + "를 찾을 수 없습니다."));
		
		log.info("[MemberService] login : {}", login);
		
		log.info("[MemberService] selectMyInfo End ====================");
		return modelMapper.map(login, LoginDto.class);
		
	}

}
