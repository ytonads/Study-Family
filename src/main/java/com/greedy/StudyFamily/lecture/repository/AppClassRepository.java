package com.greedy.StudyFamily.lecture.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.lecture.entity.AppClassWrite;
import com.greedy.StudyFamily.student.entity.Student;

public interface AppClassRepository extends JpaRepository<AppClass, Long> {
	

	// 수강신청목록
	@EntityGraph(attributePaths = {"lecture"})
	List<AppClass> findByappClassCode(Student student, Sort appClass);

	AppClassWrite save(AppClassWrite appClasswrite);

	


}
