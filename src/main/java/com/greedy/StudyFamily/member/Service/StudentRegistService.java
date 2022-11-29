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
import com.greedy.StudyFamily.member.repository.StudentRegistRepository;
import com.greedy.StudyFamily.student.entity.Student;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class StudentRegistService {

	private final AdminRepository adminRepository;
	private final StudentRegistRepository studentRegistRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final TokenProvider tokenProvider;
	
	public StudentRegistService(AdminRepository adminRepository, StudentRegistRepository studentRegistRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, TokenProvider tokenProvider) {
		this.adminRepository = adminRepository;
		this.studentRegistRepository = studentRegistRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.tokenProvider = tokenProvider;
	}
	
	
	@Transactional
	public LoginDto studentRegist(LoginDto loginDto) {
		
		log.info("[LoginService] login Start ====================");
		log.info("[LoginService] loginDto : {}", loginDto);
		
		Student student = studentRegistRepository.findByStudentRegistNum(loginDto.getStudent().getStudentRegistNum());
		
		if(student == null) {
			log.info("[StudentService] 우리 학생이 맞는지 주민등록번호 체크.");
			throw new DuplicatedLoginIdException("우리 학생이 맞는지 주민등록 번호 체크");
		}
		
		if(adminRepository.findByLoginId(loginDto.getLoginId()).isPresent()) {
			throw new DuplicatedLoginIdException("학생의 아이디가 중복되었습니다.");
		}

		Student studentCode = studentRegistRepository.findByStudentCode(loginDto.getStudent().getStudentCode());
		
		if(adminRepository.findByStudent(studentCode) != null) {
			log.info("[StudentService] 이전에 가입했는지 학생 코드의 등록여부 확인.");
			throw new DuplicatedLoginIdException("이미 생성했었습니다.");
		}
		
		loginDto.setLoginPassword(passwordEncoder.encode(loginDto.getLoginPassword()));
		
		log.info("[LoginService] loginDto : {}", loginDto);
		
		Login login = modelMapper.map(loginDto, Login.class);
		login.setStudent(student);
		
		adminRepository.save(login);
		
		log.info("[LoginService] login End ====================");
		return loginDto;
		
	}
	
	/* 2. 로그인 */
	@Transactional
	public TokenDto login(LoginDto loginDto) {
		
		log.info("[StudentService] studentlogin Start ====================");
		log.info("[StudentService] loginDto : {}", loginDto);
		
		// 1. 아이디 조회. null이면 예외처리 아니면 2,3 번 진행
		Login login = adminRepository.findByLoginId(loginDto.getLoginId())
				.orElseThrow(() -> new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다."));
	
		// 2. 비밀번호 매칭
		if(!passwordEncoder.matches(loginDto.getLoginPassword(), login.getLoginPassword())) {
			log.info("[StudentService] Password Match fail!!");
			throw new LoginFailedException("잘못 된 아이디 또는 패스워드입니다.");
		}
						
		// 3. 토큰 발급
		// semi 떄 Session을 사용한 것과 달리 이번엔 Token 사용함
		// 여러개의 서버를 사용하기 위해선 token 방식을 요즘엔 사용. 세션 사용하면 동기화 사용하는게 아닌이상 1번 서버에서 키를 부여하면 2번 서버에서 읽기 불가. 동기화시엔 기능 저하.		
		// 또한 세션 저장소에 민감한 데이터를 저장하지 않으므로 보안성도 좋음. 모든 서버들이 받는 토큰이 동일함. (확장성)
		// 주로 Json Web Token(JWT) 사용. Header(사용중인 알고리즘, 토큰 유형), Payload(발급자, 만료시간, 제목 등), Signature(무결성 확인에 사용하는 암호화 알고리즘 통해 생성된 문자열)
		TokenDto tokenDto = tokenProvider.generateTokenDto(modelMapper.map(login, LoginDto.class));
		
		log.info("[StudentService] tokenDto : {}", tokenDto);
		
		log.info("[StudentService] studentlogin End ====================");
		
		return tokenDto;
		
	}

}
