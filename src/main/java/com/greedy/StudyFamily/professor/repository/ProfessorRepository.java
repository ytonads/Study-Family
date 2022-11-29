package com.greedy.StudyFamily.professor.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.professor.entity.Professor;

public interface ProfessorRepository extends JpaRepository<Professor, Long>{

	/* professorCode로 조회 */
	Professor findByProfessorCode(Long professorCode);

}
