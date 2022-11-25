package com.greedy.StudyFamily.board.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.StudyFamily.board.entity.SubNotice;
import com.greedy.StudyFamily.board.entity.SubNoticeWrite;


public interface SubnoticeRepository extends JpaRepository<SubNotice, Long>{

	
	// 강좌공지 목록 조회
	Page<SubNotice> findByStatus(Pageable pageable, String status);

	//Page<SubNotice> findAll(Pageable pageable);
	
	// 강좌공지 상세 조회
	@Query("SELECT p " +
	         "FROM SubNotice p " +
			"WHERE p.subnoticeCode = :subnoticeCode " +
	          "AND p.status = 'Y'")
	Optional<SubNotice> findBySubNoticeCode(@Param("subnoticeCode") Long subnoticeCode);

	// 강좌공지 작성
	SubNoticeWrite save(SubNoticeWrite subnoticeWrite);
	


}
