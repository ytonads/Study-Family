package com.greedy.StudyFamily.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.greedy.StudyFamily.admin.entity.Login;

public interface LoginRepository extends JpaRepository<Login, Long> {

	//쪽지 발송 시 수신자 조회
	Optional<Login> findByLoginId(@Param("loginId") String loginId);

	

	

	
	
}
