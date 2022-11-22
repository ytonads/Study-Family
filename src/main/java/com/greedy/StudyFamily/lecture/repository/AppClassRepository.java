package com.greedy.StudyFamily.lecture.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.student.entity.Student;

public interface AppClassRepository extends JpaRepository<AppClass, Long> {
	
	@EntityGraph(attributePaths = {"lecture"})
	List<AppClass> findByAppClassCode(Student student);
}
