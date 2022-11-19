package com.greedy.StudyFamily.admin.service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.dto.TokenDto;
import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.admin.exception.LoginFailedException;
import com.greedy.StudyFamily.admin.repository.AdminRepository;
import com.greedy.StudyFamily.exception.DuplicatedUsernameException;
import com.greedy.StudyFamily.jwt.TokenProvider;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AdminService {

	private final AdminRepository adminRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final TokenProvider tokenProvider;
	
	public AdminService(AdminRepository adminRepository,  PasswordEncoder passwordEncoder, ModelMapper modelMapper,  TokenProvider tokenProvider) {
		this.adminRepository = adminRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.tokenProvider = tokenProvider;
	}
	
	
	
	//로그인
	@Transactional
	public TokenDto logint(LoginDto loginDto) {
		
		// 1. 아이디 조회
		Login login = adminRepository.findByLoginId(loginDto.getLoginId())
							.orElseThrow(() -> new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다."));
				
		// 2. 비밀번호 매칭 - passwordEncoder 통해서만 확인 가능
		if(!passwordEncoder.matches(loginDto.getLoginPassword(), login.getLoginPassword())) {//matches(사용자가 작성한 비밀번호, 인코딩된 비밀번호)
			log.info("[AuthService] password Match Fail!!!!");
			//매칭 되지 않는다면!
			throw new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다.");
		}
				
				
		// 3. 토큰 발급
		TokenDto tokenDto = tokenProvider.generateTokenDto(modelMapper.map(login, LoginDto.class));		
		
		
		
		return tokenDto;
	}



	//회원가입
//	public Object signup(LoginDto loginDto) {
//		
//		/* 이메일 중복 확인 */
//		if(adminRepository.findByTraficrId(loginDto.getLoginId()) != null) {
//			log.info("[AuthService] 이메일이 중복 됩니다.");
//			throw new DuplicatedUsernameException("이메일이 중복됩니다.");
//		}
//		
//		/* password 암호화 처리 */
//		//passwordEncoing(암호화) 처리를 하려면 꼭 Bean등록 필수!!!! - SecurityConfig.class
//		loginDto.setLoginPassword(passwordEncoder.encode(loginDto.getLoginPassword()));
//		adminRepository.save(modelMapper.map(loginDto, Login.class));
//		
//		
//		return loginDto;
//	}

}
