package com.greedy.StudyFamily.lecture.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.lecture.entity.SubPlan;

public interface SubPlanRepository extends JpaRepository<SubPlan, Long>{

	// 수업계획서 목록 조회
	Optional<SubPlan> findByPlanCode(Long planCode);
}
