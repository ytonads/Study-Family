package com.greedy.StudyFamily.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.member.entity.ProfessorRegist;
import com.greedy.StudyFamily.member.entity.StudentRegist;

public interface ProfessorRegistRepository extends JpaRepository<ProfessorRegist, Long>{

	//ProfessorRegist findByProfessorId(String professorId);
	
	ProfessorRegist findByProfessorRegistNum(String professorRegistNum);
	
	Optional<ProfessorRegist> findByProfessorId(String professorId);
	
	Optional<StudentRegist> findByStudentId(String studentId);
	
}
