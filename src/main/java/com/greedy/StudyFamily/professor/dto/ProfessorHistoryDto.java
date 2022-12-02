package com.greedy.StudyFamily.professor.dto;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class ProfessorHistoryDto {

	private Long professorModifyCode;
	private Date professorModifyDate;
	private ProfessorPositionDto professorPosition;
	
	@JsonIgnore
	private ProfessorDto professor;
	
	@JsonProperty("professor")
	public Map<String, Object> getProfessor() {
		
		Map<String, Object> map = new HashMap<>();
		map.put("professorCode", professor.getProfessorCode());
		map.put("professorName", professor.getProfessorName());
		map.put("professor", professor.getProfessorPosition());
		map.put("professor", professor.getProfessorHireDate());
		map.put("professor", professor.getProfessorRegistNum());
		map.put("professor", professor.getProfessorPhone());
		map.put("professor", professor.getProfessorAddress());
		map.put("professor", professor.getProfessorStatus());
		map.put("professor", professor.getProfessorEmail());
		map.put("professor", professor.getDepartment());
		
		return map;
	}
	
}
