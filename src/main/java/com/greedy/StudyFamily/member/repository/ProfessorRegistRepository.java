package com.greedy.StudyFamily.member.repository;

import java.util.Optional;


import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.professor.entity.Professor;

public interface ProfessorRegistRepository extends JpaRepository<Professor, Long>{

	//ProfessorRegist findByProfessorId(String professorId);
	
	Professor findByProfessorRegistNum(String professorRegistNum);
	
	//Optional<Professor> findByProfessorId(String professorId);
	
	//Optional<StudentRegist> findByStudentId(String studentId);
	
	/* professor의 professorCode 조회 */
	Professor findByProfessorCode(Long professorCode);
	
}
