package com.greedy.StudyFamily.lecture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.entity.SubPlan;
import com.greedy.StudyFamily.lecture.entity.SubPlanWrite;

public interface SubPlanRepository extends JpaRepository<SubPlan, Long>{

	// 수업계획서 목록 조회
	Optional<SubPlan> findByPlanCode(Long planCode);

	SubPlanWrite save(SubPlanWrite subPlanWrite);

	Optional<SubPlan> findByLecture(Lecture lectureCode);

	
}
