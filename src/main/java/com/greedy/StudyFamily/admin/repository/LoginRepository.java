package com.greedy.StudyFamily.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

	
	
	
	//쪽지 조회
	Optional<Login> findByLoginId(String loginId);

	//쪽지 발송 - 수신자
	Login findByLoginId(LoginDto sender);
	

	

	
	
}
