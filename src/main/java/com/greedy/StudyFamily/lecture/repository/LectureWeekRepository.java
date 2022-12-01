package com.greedy.StudyFamily.lecture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.entity.LectureWeek;
import com.greedy.StudyFamily.student.entity.Student;

public interface LectureWeekRepository extends JpaRepository<LectureWeek, Long> {

	Optional<LectureWeek> findByLectures_LectureCode(Long lectureCode);





}
