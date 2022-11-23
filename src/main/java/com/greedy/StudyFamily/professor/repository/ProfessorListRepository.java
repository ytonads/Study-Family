package com.greedy.StudyFamily.professor.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.professor.entity.Professor;

public interface ProfessorListRepository extends JpaRepository<Professor, Long>{

	/* 교수 목록 조회 */
	@EntityGraph(attributePaths= {"professorCode"})
	Page<Professor> findAll(Pageable pageable);
	
	
}
