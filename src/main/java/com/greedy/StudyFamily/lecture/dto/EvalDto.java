package com.greedy.StudyFamily.lecture.dto;

import java.util.HashMap;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EvalDto {

	private Long evalCode;
	private EvalStandardDto evalStandard;
	private String evalGrade;
	private Long evalResult;
	private Long evalMiddle;
	private Long evalFinal;
	private Long evalTask;
	private Long evalAttend;
	
	@JsonIgnore
	private AppClassDto appClass;

	@JsonProperty
	public Map<String, Object> getAppClass() {
		
		Map<String, Object> map = new HashMap<>();
		
		map.put("appClassCode", appClass.getAppClassCode());
		
		return map;
	}
	
}
