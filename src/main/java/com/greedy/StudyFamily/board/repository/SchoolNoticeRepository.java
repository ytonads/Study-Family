package com.greedy.StudyFamily.board.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.StudyFamily.board.entity.SchoolNotice;

public interface SchoolNoticeRepository extends JpaRepository<SchoolNotice, Long>{
	
	/* 공지 목록 조회 */
	@EntityGraph(attributePaths= {"schoolNoticeCode"})
	Page<SchoolNotice> findBySchoolNoticeState(Pageable pageable, String schoolNoticeState);

	/* 공지 목록 조회 - 검색 */
	@EntityGraph(attributePaths= {"schoolNoticeCategory"})
	Page<SchoolNotice> findBySchoolNoticeCategoryAndSchoolNoticeState
	(Pageable pageable, String schoolNoticeCategory, String schoolNoticeState);
	
	/* 공지 상세 조회 */
	
	@Query("SELECT s " +
			 "FROM SchoolNotice s " +
			"WHERE s.schoolNoticeCode = :schoolNoticeCode " +
			  "AND s.schoolNoticeState = 'Y'")
	Optional<SchoolNotice> findBySchoolNoticeCode(@Param("schoolNoticeCode") Long schoolNoticeCode);
	
	
	
	
	
}
