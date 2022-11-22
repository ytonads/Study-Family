package com.greedy.StudyFamily.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.admin.entity.Login;

// 데이터 타입 : Login, 키값 : Long
public interface AdminRepository extends JpaRepository<Login, Long> {

	// 나중에 주민번호 중복 체크 진행하자.
//	/* 아이디 중복 체크(관리자는 이메일이 없으므로 아이디 중복체크만 진행) */
//	Login findByLoginId(String loginId);

	/* memberId로 조회. Optional을 쓴 이유는 아이디가 적혀있을 경우 적혀있지 않아서 null일 경우 둘 다 받음 */
	Optional<Login> findByLoginId(String loginId);

}
