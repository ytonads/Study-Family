package com.greedy.StudyFamily.lecture.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.board.entity.Subnotice;

public interface SubnoticeRepository extends JpaRepository<Subnotice, Long>{

	// 강좌공지 목록 조회
	Page<Subnotice> findAll(Pageable pageable);
	


}
