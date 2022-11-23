package com.greedy.StudyFamily.admin.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.admin.entity.File;

public interface FileRepository extends JpaRepository<File, Long> {

}
