package com.greedy.StudyFamily.lecture.repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.professor.entity.Professor;

public interface LectureRepository extends JpaRepository<Lecture, Long>{

	//강의실 조회 - 교수
	Page<Lecture> findByProfessor(Pageable pageable, Professor findProfessor);


	
	//강의실 상세 조회 - 학생
//	Optional<Lecture> findByLectureCode(@Param("lectureCode") Long lectureCode);

	

}
