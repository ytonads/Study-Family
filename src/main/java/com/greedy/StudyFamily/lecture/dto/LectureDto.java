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
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LectureDto {
	
	private final static String FILE_URL = "http://localhost:8001/files/";

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
		 
		 //file이 0이 아닐때만 가져오는 조건
		 if(lectureWeek.getFile().size()!=0) {
			 //파일을 넣기 위한 가공
			 lectureWeek.getFile().get(0).setSavedRoute("http://localhost:8001/files/" + lectureWeek.getFile().get(0).getSavedRoute());
			 
			 map.put("savedRoute", lectureWeek.getFile().get(0).getSavedRoute());
			 map.put("originName", lectureWeek.getFile().get(0).getOriginName());
			 map.put("startDate", lectureWeek.getFile().get(0).getStartDate());
			 map.put("endDate", lectureWeek.getFile().get(0).getEndDate());
			 map.put("lectureWeekInFile", lectureWeek.getFile().get(0).getLectureWeek());
			 map.put("fileType", lectureWeek.getFile().get(0).getFileType());
			 map.put("fileCode", lectureWeek.getFile().get(0).getFileCode());
			 
		 }
		 
		 //courseHistory가 0이 아닐때만 가져온다.
		 if(lectureWeek.getCourseHistories().size()!=0) {
			 
			 map.put("courseStatus", lectureWeek.getCourseHistories().get(0).getCourseStatus());
			 map.put("courseTime", lectureWeek.getCourseHistories().get(0).getCourseTime());

		 }
		 
		 
		 return map;
	 }
	
	
}
