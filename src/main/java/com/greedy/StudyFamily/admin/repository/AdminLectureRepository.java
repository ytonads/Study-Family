package com.greedy.StudyFamily.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.subject.entity.Subject;

public interface AdminLectureRepository extends JpaRepository<Lecture, Long>{

	Lecture findBylectureCode(Long lectureCode);


	
}
