package com.greedy.StudyFamily.lecture.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.StudyFamily.lecture.entity.Eval;

public interface EvalRepository extends JpaRepository<Eval, Long> {

	
	/* 학생 평가 등록하기 */
	@EntityGraph(attributePaths= {"evalCode", "lectureCode"})
	@Query("SELECT e " +
			"FROM Eval e " +
			"WHERE e.evalCode =:evalCode " +
			"AND e.lecture.lectureCode =:lectureCode ")
	Eval findByEvalCodeAndLecture(@Param("lectureCode") Long lectureCode, @Param("evalCode") Long evalCode);


}
