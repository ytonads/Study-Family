package com.greedy.StudyFamily.lecture.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.student.entity.Student;

public interface AppClassRepository extends JpaRepository<AppClass, Long> {
	

	// 수강신청목록
	@EntityGraph(attributePaths = {"lecture"})
	List<AppClass> findByappClassCode(Student student, Sort appClass);

	
	//메세지 발송 시 보여줄 친구목록
//	@Query("SELECT a, s " +
//			"FROM AppClass a, Student s, Lecture l " +
//			"WHERE a.student.studentNo = s.studentNo " +
//			"AND a.lecture.lectureCode = l.lectureCode")
	Page<AppClass> findByLecture(Pageable pageable, Lecture findLecture);


}
