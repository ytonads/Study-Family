package com.greedy.StudyFamily.lecture.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.student.entity.Student;

public interface AppclassRepository extends JpaRepository<AppClass, Long> {

	



}
