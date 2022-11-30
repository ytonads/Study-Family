package com.greedy.StudyFamily.lecture.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.CourseHistory;

public interface CourseRepository extends JpaRepository<CourseHistory, Long> {

}
