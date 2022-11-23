package com.greedy.StudyFamily.student.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.student.entity.Student;

public interface StudentRepository extends JpaRepository<Student, Long>{

	/* [학생] 내 정보 조회 - studentNo로 조회 */
	Optional<Student> findByStudentNo(Long studentNo);



//	Optional<Student> findByStudentNo(Long studentNo);

}
