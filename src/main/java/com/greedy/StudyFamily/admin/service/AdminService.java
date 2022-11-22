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
	
//	/* 1. 관리자 포함 회원가입 */
//	@Transactional
//	public LoginDto regist(LoginDto loginDto) {
//		
//		log.info("[AuthService] regist Start ====================");
//		log.info("[AuthService] loginDto : {}", loginDto);
//		
//		if(adminRepository.findByLoginId(loginDto.getLoginId()) != null) {
//			log.info("[AuthService] 아이디 중복입니다.");
//			throw new DuplicatedLoginIdException("아이디 중복됩니다.");
//		}
//		
//		// 암호화하는 메서드로 암호화하고 저장함. save 통해 우리가 전달받은 dto 값을 Member 타입 엔티티로 바꾼다.
//		loginDto.setLoginPassword(passwordEncoder.encode(loginDto.getLoginPassword()));
//		adminRepository.save(modelMapper.map(loginDto, Login.class));
//		
//		log.info("[AuthService] regist End ====================");
//		return loginDto;
//		
//	}
	
	/* 2. 로그인 */
	@Transactional
	public TokenDto login(LoginDto loginDto) {
		
		log.info("[AuthService] login Start ====================");
		log.info("[AuthService] loginDto : {}", loginDto);
		
		// 1. 아이디 조회. null이면 예외처리 아니면 2,3 번 진행
		Login login = adminRepository.findByLoginId(loginDto.getLoginId())
							.orElseThrow(() -> new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다."));
		
		// 2. 비밀번호 매칭
		if(!passwordEncoder.matches(loginDto.getLoginPassword(), login.getLoginPassword())) {
			log.info("[AuthService] Password Match Fail!!!");
			throw new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다.");
			
		}
						
		// 3. 토큰 발급
		// semi 떄 Session을 사용한 것과 달리 이번엔 Token 사용함
		// 여러개의 서버를 사용하기 위해선 token 방식을 요즘엔 사용. 세션 사용하면 동기화 사용하는게 아닌이상 1번 서버에서 키를 부여하면 2번 서버에서 읽기 불가. 동기화시엔 기능 저하.		
		// 또한 세션 저장소에 민감한 데이터를 저장하지 않으므로 보안성도 좋음. 모든 서버들이 받는 토큰이 동일함. (확장성)
		// 주로 Json Web Token(JWT) 사용. Header(사용중인 알고리즘, 토큰 유형), Payload(발급자, 만료시간, 제목 등), Signature(무결성 확인에 사용하는 암호화 알고리즘 통해 생성된 문자열)
		TokenDto tokenDto = tokenProvider.generateTokenDto(modelMapper.map(login, LoginDto.class));
		
		log.info("[AuthService] tokenDto : {}", tokenDto);
		
		log.info("[AuthService] login End ====================");
		
		return tokenDto;
		
	}

}
