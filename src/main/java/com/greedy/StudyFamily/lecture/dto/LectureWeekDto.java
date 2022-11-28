package com.greedy.StudyFamily.lecture.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.greedy.StudyFamily.admin.dto.FileDto;
import com.greedy.StudyFamily.admin.entity.File;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class LectureWeekDto {

	private Long lectureWeekCode;
	private String week;
	private String fileDivision;
	private LectureDto lectures;
	private List<File> file;
	
	
	/* 출결 상태를 위한 참조 */
	@JsonIgnore
	private List<CourseHistoryDto> courseHistories;

	@JsonProperty("courseHistories")
	public List<Map<String, Object>> getCourseHistories(){
		return courseHistories.stream().map(courseHis -> dtoToMap(courseHis)).toList();
	}
	
	public Map<String, Object> dtoToMap(CourseHistoryDto courseHis){
		
		Map<String, Object> map = new HashMap<>();
		map.put("lectureWeek", courseHis.getLectureWeek().getWeek());
		map.put("courseTime", courseHis.getCourseTime());				//수강시간
		map.put("courseStatus", courseHis.getCourseStatus());			//수강상태(출석)
		map.put("student", courseHis.getStudent().getStudentCode());	//학생
		
		return map;
	}
	
	
	
	
}
