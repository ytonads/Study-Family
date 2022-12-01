package com.greedy.StudyFamily.admin.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greedy.StudyFamily.admin.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {


	

	

}
