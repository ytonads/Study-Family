package com.greedy.StudyFamily.lecture.repository;


import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.student.entity.Student;

public interface LectureRepository extends JpaRepository<Lecture, Long>{

	/* 강의실 조회(학생) - 완료!!! */
	@EntityGraph(attributePaths= {"professor", "subject"})
	@Query("SELECT l " +
			"FROM Lecture l, AppClass a, Student s " +
			"WHERE l.lectureCode = a.lecture.lectureCode " +
			"AND a.student.studentNo = s.studentNo " +
			"AND s.studentNo = :studentNo")
	List<Lecture> findByStu(@Param("studentNo")Long studentNo);	
	

	/* 강의실 조회(교수) - 완료!!! */
	@Query("SELECT l " +
			"FROM Lecture l " +
			"WHERE l.professor.professorCode = :professorCode")
	List<Lecture> findByProfessor(@Param("professorCode")Long professorCode);
	
	
	/* 강좌 상세 조회(학생, 교수) - 완료!!! */
	@EntityGraph(attributePaths= {"professor"})
	Lecture findByLectureCode(Long lectureCode);
	
	
	//수강신청 orElseThrow 동작 코드
	@Query("SELECT l " +
	        "FROM Lecture l " +
			"WHERE l.lectureCode = :lectureCode " )
	Optional<Lecture> findByLecture(@Param("lectureCode") Long lectureCode);


	
	//수강신청 리스트 조회
	Page<Lecture> findAll(Pageable pageable);


	Optional<Lecture> findByLectureCode(LectureDto lectureDto);




	
	

	

	

}
