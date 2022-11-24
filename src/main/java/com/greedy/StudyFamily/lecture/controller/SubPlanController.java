package com.greedy.StudyFamily.lecture.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.lecture.service.SubPlanService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("/subPlan")
public class SubPlanController {

private final SubPlanService subPlanService;
	
	public SubPlanController(SubPlanService subPlanService) {
		this.subPlanService = subPlanService;	
	}
	
	//수업계획서 목록 조회
		@GetMapping("/list/{planCode}")
		public ResponseEntity<ResponseDto> selectPlanList(@PathVariable Long planCode) {

			return ResponseEntity
					.ok()
					.body(new ResponseDto(HttpStatus.OK, "수업계획서 조회 성공", subPlanService.selectPlanList(planCode)));

		}	
}
