package com.greedy.StudyFamily.subject.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.subject.entity.Department;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

}
