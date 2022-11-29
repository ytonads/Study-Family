package com.greedy.StudyFamily.member.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.member.entity.StudentRegist;
import com.greedy.StudyFamily.student.entity.Student;

public interface StudentRegistRepository extends JpaRepository<Student, Long>{

	//StudentRegist findByStudentId(String studentId);
	
//	StudentRegist findByStudentRegistNum(String studentRegistNum);
//	
//	Optional<StudentRegist> findByStudentId(String studentId);
	
	//Optional<ProfessorRegist> findByProfessorId(String professorId);

	Student findByStudentRegistNum(String studentRegistNum);
	
	Student findByStudentCode(Long studentCode);
	
}
