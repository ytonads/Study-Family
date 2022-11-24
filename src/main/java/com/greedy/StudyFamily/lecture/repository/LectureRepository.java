package com.greedy.StudyFamily.lecture.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.student.entity.Student;

public interface LectureRepository extends JpaRepository<Lecture, Long>{

	
	//강의실 조회 - 교수
	@EntityGraph(attributePaths= {"subject", "professor"})
	Page<Lecture> findByProfessor(Pageable pageable, Professor findProfessor);

	
	
	//강의실 조회 - 학생
	@EntityGraph(attributePaths= {"subject", "professor"})
	@Query("SELECT l " +
			"FROM Lecture l, AppClass a, Student s " +
			"WHERE l.lectureCode = a.lecture.lectureCode " +
			"AND a.student.studentNo = s.studentNo")
	Page<Lecture> findByStudent(Pageable pageable, Student findStudent);



	
	//이거 써야 수강신청 orElseThrow 동작함(강좌 상세 조회 대신할 코드)
	//@Query("SELECT l " +
	//        "FROM Lecture l " +
	//		"WHERE l.lectureCode = :lectureCode " )
	//Optional<Lecture> findByLectureCode(@Param("lectureCode") Long lectureCode);
	
	//강좌 상세 조회 - 학생!!&교수
	Lecture findByLectureCode(Long lectureCode);

	//수강신청 리스트 조회
	Page<Lecture> findAll(Pageable pageable);
	

	

	

}
