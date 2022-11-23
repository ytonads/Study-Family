package com.greedy.StudyFamily.board.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.board.dto.QaBoardDto;
import com.greedy.StudyFamily.board.entity.QaBoard;

public interface QaBoardRepository extends JpaRepository<QaBoard, Long>{


	Page<QaBoard> findByqaStatus(Pageable pageable, String qaStatus);
	

	
}
