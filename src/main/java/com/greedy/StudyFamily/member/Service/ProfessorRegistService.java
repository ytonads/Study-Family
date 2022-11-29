package com.greedy.StudyFamily.member.Service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.dto.TokenDto;
import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.admin.repository.AdminRepository;
import com.greedy.StudyFamily.exception.DuplicatedLoginIdException;
import com.greedy.StudyFamily.exception.LoginFailedException;
import com.greedy.StudyFamily.jwt.TokenProvider;
import com.greedy.StudyFamily.member.repository.ProfessorRegistRepository;
import com.greedy.StudyFamily.professor.entity.Professor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfessorRegistService {

	private final AdminRepository adminRepository;
	private final ProfessorRegistRepository professorRegistRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final TokenProvider tokenProvider;
	
	public ProfessorRegistService(AdminRepository adminRepository, ProfessorRegistRepository professorRegistRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, TokenProvider tokenProvider) {
		this.adminRepository = adminRepository;
		this.professorRegistRepository = professorRegistRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.tokenProvider = tokenProvider;
	}
	
	/* 1 */

	@Transactional
	public LoginDto professorRegist(LoginDto loginDto) {
		
		log.info("[LoginService] login Start ====================");
		log.info("[LoginService] loginDto : {}", loginDto);
		
		Professor professor = professorRegistRepository.findByProfessorRegistNum(loginDto.getProfessor().getProfessorRegistNum());
		
		if(professor == null) {
			log.info("[ProfessorService] 우리 교수가 맞는지 주민등록번호 체크.");
			throw new DuplicatedLoginIdException("우리 교수가 맞는지 주민등록번호 체크.");
		}
		
		// optional 처리시 필요
		if(adminRepository.findByLoginId(loginDto.getLoginId()).isPresent()) {
			throw new DuplicatedLoginIdException("교수님의 아이디가 중복되었습니다.");
		}
		
		Professor professorCode = professorRegistRepository.findByProfessorCode(loginDto.getProfessor().getProfessorCode());
		
		// adminRepository에 해당 교수코드 있는지 체크 필요 (같은 사람이 여러 아이디 못 만들도록)
		//if(professorCode.getProfessorCode() == loginDto.getProfessor().getProfessorCode()) {
		if(adminRepository.findByProfessor(professorCode) != null) {
		//if(professorRegistRepository.findByProfessorCode(loginDto.getProfessor().getProfessorCode()).isPesent) {
		//if(professor.equals(loginDto.getProfessor().getProfessorCode())) {
			log.info("[ProfessorService] 이전에 가입했는지 교수 코드의 등록여부 확인.");
			throw new DuplicatedLoginIdException("이미 생성하셨었습니다.");
		}
		
		loginDto.setLoginPassword(passwordEncoder.encode(loginDto.getLoginPassword()));
		
		log.info("[LoginService] loginDto : {}", loginDto);
		
		Login login = modelMapper.map(loginDto, Login.class);
		login.setProfessor(professor);
		
		adminRepository.save(login);
	
		log.info("[LoginService] login End ====================");
		return loginDto;
		
	}
	
	/* 2. 로그인 */
	@Transactional
	public TokenDto login(LoginDto loginDto) {
		
		log.info("[ProfessorService] professorlogin Start ====================");
		log.info("[ProfessorService] loginDto : {}", loginDto);
		
		// 1. 아이디 조회. null이면 예외처리 아니면 2,3 번 진행
		Login login = adminRepository.findByLoginId(loginDto.getLoginId())
				.orElseThrow(() -> new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다."));
	
		// 2. 비밀번호 매칭
		if(!passwordEncoder.matches(loginDto.getLoginPassword(), login.getLoginPassword())) {
			log.info("[ProfessorService] Password Match fail!!");
			throw new LoginFailedException("잘못 된 아이디 또는 패스워드입니다.");
		}
						
		// 3. 토큰 발급
		// semi 떄 Session을 사용한 것과 달리 이번엔 Token 사용함
		// 여러개의 서버를 사용하기 위해선 token 방식을 요즘엔 사용. 세션 사용하면 동기화 사용하는게 아닌이상 1번 서버에서 키를 부여하면 2번 서버에서 읽기 불가. 동기화시엔 기능 저하.		
		// 또한 세션 저장소에 민감한 데이터를 저장하지 않으므로 보안성도 좋음. 모든 서버들이 받는 토큰이 동일함. (확장성)
		// 주로 Json Web Token(JWT) 사용. Header(사용중인 알고리즘, 토큰 유형), Payload(발급자, 만료시간, 제목 등), Signature(무결성 확인에 사용하는 암호화 알고리즘 통해 생성된 문자열)
		TokenDto tokenDto = tokenProvider.generateTokenDto(modelMapper.map(login, LoginDto.class));
		
		log.info("[professorService] tokenDto : {}", tokenDto);
		
		log.info("[professorService] professorlogin End ====================");
		
		return tokenDto;
		
	}

}
