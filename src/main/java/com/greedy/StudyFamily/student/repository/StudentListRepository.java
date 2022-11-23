package com.greedy.StudyFamily.student.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.student.entity.Student;

public interface StudentListRepository extends JpaRepository<Student, Long>{
	
	/* 학생 목록 조회 */
	@EntityGraph(attributePaths= {"studentNo"})
	Page<Student> findAll(Pageable pageable);

	

}
