package com.greedy.StudyFamily.lecture.repository;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.lecture.entity.AppClassWrite;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.student.entity.Student;


public interface AppClassRepository extends JpaRepository<AppClass, Long> {
	

	//수강신청 레파
	AppClassWrite save(AppClassWrite appClasswrite);

	
	/* 수강생 리스트 조회(쪽지) - 완료!!! */
	@EntityGraph(attributePaths= {"lecture", "student"})
	Page<AppClass> findByLecture(Pageable pageable, Lecture findLecture);

	// 수강신청목록
	@EntityGraph(attributePaths = {"lecture"})
	List<AppClass> findByStudent(Student student, Sort appClass);


}
