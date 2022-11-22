package com.greedy.StudyFamily.lecture.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greedy.StudyFamily.admin.dto.FileDto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

//@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
@ToString
public class LectureWeekDto {

	private Long lectureWeekCode;
	private String week;
	private String fileDivision;
	private LectureDto lectures;
	
//	@JsonIgnore
//	private FileDto files;
	
//	@JsonProperty("files")
//	public Set<String> getFiles(){
//		return files.stream().map(file -> dtoToMap(file)).toList();
//	}
//	
//	public Map<String, Object> dtoToMap(FileDto file){
//		
//		Map<String, Object> map = new HashMap<>();
//		map.put("startDate", file.getStartDate());
//		map.put("endDate", file.getEndDate());
//		
//		return map;
//	}
	
}
