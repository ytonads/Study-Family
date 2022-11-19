package com.greedy.StudyFamily.lecture.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.greedy.StudyFamily.lecture.entity.Lecture;

public interface LectureRepository extends JpaRepository<Lecture, Long>{

	//강의실 리스트 조회
//	@EntityGraph(attributePaths= {"subject", "professor"})
	Page<Lecture> findAll(Pageable pageable);
	
	
	//강의실 상세 조회 - 학생
	Optional<Lecture> findByLectureCode(@Param("lectureCode") Long lectureCode);

	

}
