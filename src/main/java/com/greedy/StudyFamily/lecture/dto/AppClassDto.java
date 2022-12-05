package com.greedy.StudyFamily.lecture.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greedy.StudyFamily.student.dto.StudentDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AppClassDto {

	private Long appClassCode;
	@JsonIgnore
	private LectureDto lecture;
	@JsonIgnore
	private StudentDto student;
	
	@JsonProperty("lecture")
	public Map<String, Object> getLecture() {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("lectureCode", lecture.getLectureCode());
		map.put("subject", lecture.getSubject());
		map.put("lectureName", lecture.getLectureName());
		
		return map;
	}

	@JsonProperty("student")
	public Map<String, Object> getStudent() {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("studentNo", student.getStudentNo());
		map.put("studentName", student.getStudentName());
		map.put("department", student.getDepartment());
		
		return map;
	}
	
	
}
