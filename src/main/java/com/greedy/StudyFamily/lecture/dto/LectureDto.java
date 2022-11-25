package com.greedy.StudyFamily.lecture.dto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.subject.dto.SubjectDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureDto {

	private Long lectureCode;
	private SubjectDto subject;
	private Long capacity;
	private ProfessorDto professor;
	private String lectureName;
	private Long lecturePersonnel;
	private String openingDate;
	
	@JsonIgnore
	private List<LectureWeekDto> lectureWeeks;
	
	 @JsonProperty("lectureWeeks")
	 public List<Map<String, Object>> getLectureWeeks() {
	    return lectureWeeks.stream().map(lectureWeek -> dtoToMap(lectureWeek)).toList();
	 }
	 
	 public Map<String, Object> dtoToMap(LectureWeekDto lectureWeek){
		 
		 Map<String, Object> map = new HashMap<>();
		 map.put("lectureWeekCode", lectureWeek.getLectureWeekCode());
		 map.put("week", lectureWeek.getWeek());
		 map.put("fileDivision", lectureWeek.getFileDivision());
		 map.put("fileCode", lectureWeek.getFiles().getFileCode());
		 map.put("originName", lectureWeek.getFiles().getOriginName());
		 map.put("savedRoute", lectureWeek.getFiles().getSavedRoute());
//		 map.put("startDate", lectureWeek.getFiles().getStartDate());
//		 map.put("endDate", lectureWeek.getFiles().getEndDate());
		 map.put("lectureWeekCode", lectureWeek.getFiles().getLectureWeekCode());
		 
		 return map;
	 }
	
	
}
