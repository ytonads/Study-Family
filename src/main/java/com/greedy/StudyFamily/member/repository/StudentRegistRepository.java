package com.greedy.StudyFamily.member.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.member.entity.ProfessorRegist;
import com.greedy.StudyFamily.member.entity.StudentRegist;

public interface StudentRegistRepository extends JpaRepository<StudentRegist, Long>{

	//StudentRegist findByStudentId(String studentId);
	
	StudentRegist findByStudentRegistNum(String studentRegistNum);
	
	Optional<StudentRegist> findByStudentId(String studentId);
	
	Optional<ProfessorRegist> findByProfessorId(String professorId);
	
}
