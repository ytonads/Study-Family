package com.greedy.StudyFamily.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.subject.entity.Subject;

public interface AdminSubjectRepository extends JpaRepository<Subject, Long>{

	Subject findBysubCode(Long subCode);
	

	

}
