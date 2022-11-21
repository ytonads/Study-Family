package com.greedy.StudyFamily.lecture.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.entity.LectureWeek;
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



	//강좌 상세 조회
	Lecture findByLectureCode(Long lectureCode);

	
	

	

	


	

	

}
