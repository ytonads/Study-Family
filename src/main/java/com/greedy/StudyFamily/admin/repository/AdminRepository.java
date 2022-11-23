package com.greedy.StudyFamily.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.admin.entity.Login;

public interface AdminRepository extends JpaRepository<Login, Long> {

	Optional<Login> findByLoginId(String loginId);

//	Login findByTraficrId(String loginId);



}
