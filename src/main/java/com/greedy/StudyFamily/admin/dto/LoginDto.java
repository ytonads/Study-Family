package com.greedy.StudyFamily.admin.dto;

import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.Data;

@Data
public class LoginDto {

	private String loginId;
	private String loginPassword;
	private ProfessorDto professorCode;
	private StudentDto studentNo;
	
}
