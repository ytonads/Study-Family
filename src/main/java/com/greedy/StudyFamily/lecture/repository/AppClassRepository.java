package com.greedy.StudyFamily.lecture.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.util.Streamable;

import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.lecture.entity.AppClassWrite;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.student.entity.Student;


public interface AppClassRepository extends JpaRepository<AppClass, Long> {
	

	//수강신청 레파
	AppClassWrite save(AppClassWrite appClasswrite);

	
	//메세지 발송 시 보여줄 친구목록
	@Query("SELECT a, s " +
			"FROM AppClass a, Student s, Lecture l " +
			"WHERE a.student.studentNo = s.studentNo " +
			"AND a.lecture.lectureCode = l.lectureCode")
	Page<AppClass> findByLecture(Pageable pageable, Lecture findLecture);

	// 수강신청목록
	@EntityGraph(attributePaths = {"lecture"})
	List<AppClass> findByStudent(Student student, Sort appClass);

	//수강취소
	Optional<AppClass> findByLecture(Long lectureCode);

}
