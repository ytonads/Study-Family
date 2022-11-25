package com.greedy.StudyFamily.member.Service;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.TokenDto;
import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.admin.repository.AdminRepository;
import com.greedy.StudyFamily.exception.DuplicatedLoginIdException;
import com.greedy.StudyFamily.exception.LoginFailedException;
import com.greedy.StudyFamily.exception.UserNotFoundException;
import com.greedy.StudyFamily.jwt.TokenProvider;
import com.greedy.StudyFamily.member.dto.ProfessorRegistDto;
import com.greedy.StudyFamily.member.dto.StudentRegistDto;
import com.greedy.StudyFamily.member.entity.ProfessorRegist;
import com.greedy.StudyFamily.member.repository.ProfessorRegistRepository;
import com.greedy.StudyFamily.member.repository.StudentRegistRepository;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ProfessorRegistService {

	private final ProfessorRegistRepository professorRegistRepository;
	private final StudentRegistRepository studentRegistRepository;
	private final PasswordEncoder passwordEncoder;
	private final ModelMapper modelMapper;
	private final TokenProvider tokenProvider;
	
	public ProfessorRegistService(ProfessorRegistRepository professorRegistRepository, StudentRegistRepository studentRegistRepository, PasswordEncoder passwordEncoder, ModelMapper modelMapper, TokenProvider tokenProvider) {
		this.professorRegistRepository = professorRegistRepository;
		this.studentRegistRepository = studentRegistRepository;
		this.passwordEncoder = passwordEncoder;
		this.modelMapper = modelMapper;
		this.tokenProvider = tokenProvider;
	}
	
	/* 1. 교수 전용 회원가입 */
	@Transactional
	public ProfessorRegistDto professorRegist(ProfessorRegistDto professorRegistDto, StudentRegistDto studentRegistDto) {
		
		log.info("[ProfessorService] regist Start ====================");
		log.info("[ProfessorService] professorRegistDto : {}", professorRegistDto);
		
		if(professorRegistRepository.findByProfessorRegistNum(professorRegistDto.getProfessorRegistNum()) != null) {
			log.info("[ProfessorService] 우리 교수가 맞는지 주민등록번호 체크.");
			throw new DuplicatedLoginIdException("우리 교수가 맞는지 주민등록번호 체크.");
		}
		
		if(professorRegistRepository.findByProfessorId(professorRegistDto.getProfessorId()) == null) {
			log.info("[ProfessorService] 다른 교수 아이디와 중복입니다.");
			throw new DuplicatedLoginIdException("다른 교수 아이디와 중복됩니다.");
		}
		
		if(studentRegistRepository.findByStudentId(studentRegistDto.getStudentId()) == null) {
			log.info("[studentRepository] 다른 학생 아이디와 중복입니다.");
			throw new DuplicatedLoginIdException("다른 학생 아이디와 중복됩니다.");
		}
		
		professorRegistDto.setProfessorPassword(passwordEncoder.encode(professorRegistDto.getProfessorPassword()));
		professorRegistRepository.save(modelMapper.map(professorRegistDto, ProfessorRegist.class));
	
		log.info("[ProfessorService] regist End ====================");
		return professorRegistDto;
		
	}
	
	/* 2. 로그인 */
	@Transactional
	public TokenDto login(ProfessorRegistDto professorRegistDto) {
		
		log.info("[ProfessorService] professorlogin Start ====================");
		log.info("[ProfessorService] professorRegistDto : {}", professorRegistDto);
		
		// 1. 아이디 조회. null이면 예외처리 아니면 2,3 번 진행
		ProfessorRegist professorRegist = professorRegistRepository.findByProfessorId(professorRegistDto.getProfessorId())
							.orElseThrow(() -> new LoginFailedException("잘못 된 아이디 또는 비밀번호입니다."));
	
		// 2. 비밀번호 매칭
		if(!passwordEncoder.matches(professorRegistDto.getProfessorPassword(), professorRegistDto.getProfessorPassword())) {
			log.info("[ProfessorService] Password Match fail!!");
			throw new LoginFailedException("잘못 된 아이디 또는 패스워드입니다.");
		}
						
		// 3. 토큰 발급
		// semi 떄 Session을 사용한 것과 달리 이번엔 Token 사용함
		// 여러개의 서버를 사용하기 위해선 token 방식을 요즘엔 사용. 세션 사용하면 동기화 사용하는게 아닌이상 1번 서버에서 키를 부여하면 2번 서버에서 읽기 불가. 동기화시엔 기능 저하.		
		// 또한 세션 저장소에 민감한 데이터를 저장하지 않으므로 보안성도 좋음. 모든 서버들이 받는 토큰이 동일함. (확장성)
		// 주로 Json Web Token(JWT) 사용. Header(사용중인 알고리즘, 토큰 유형), Payload(발급자, 만료시간, 제목 등), Signature(무결성 확인에 사용하는 암호화 알고리즘 통해 생성된 문자열)
		TokenDto tokenDto = tokenProvider.generateTokenDto(modelMapper.map(professorRegist, ProfessorRegistDto.class));
		
		log.info("[professorService] tokenDto : {}", tokenDto);
		
		log.info("[professorService] professorlogin End ====================");
		
		return tokenDto;
		
	}

}
