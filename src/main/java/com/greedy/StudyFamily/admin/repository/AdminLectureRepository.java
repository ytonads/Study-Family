package com.greedy.StudyFamily.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.Lecture;

public interface AdminLectureRepository extends JpaRepository<Lecture, Long>{

}
