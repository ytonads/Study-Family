package com.greedy.StudyFamily.calendar.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.calendar.dto.CalendarDto;
import com.greedy.StudyFamily.calendar.service.CalendarService;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.subject.dto.DepartmentDto;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController	// 반환값이 응답데이터 되도록 일괄처리 하기 위해 사용
@RequestMapping("/calendar")
public class CalendarController {

	private final CalendarService calendarService;
	
	public CalendarController(CalendarService calendarService) {
		this.calendarService = calendarService;
	}
	
	/* showing 캘린더 */
	@GetMapping("/calendarView")
	public ResponseEntity<ResponseDto> showingCalendar(DepartmentDto departmentDto) {
				
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "showing 캘린더", calendarService.showingCalendar(departmentDto)));
		
	}
	
	@GetMapping("/calendarView/{calendarCode}")
	public ResponseEntity<ResponseDto> selectCalendar (Long departmentCode) {
	
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "상세 조회", calendarService.selectCalendar(departmentCode)));
	
	}
	
	@PostMapping("/calendarView")
	public ResponseEntity<ResponseDto> insertCalendar (DepartmentDto departmentDto, @ModelAttribute CalendarDto calendarDto) {
		
		//calendarDto.setDepartment(departmentDto.getDepartmentCode());
		
		log.info("[CalendarController] calendarDto : {} ", calendarDto);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "일정 입력 성공", calendarService.insertCalendar(calendarDto)));
	}
		
	@PutMapping("/calendarView")
	public ResponseEntity<ResponseDto> updateCalendar (DepartmentDto departmentDto, @ModelAttribute CalendarDto calendarDto) {
		
		calendarDto.setDepartment(departmentDto);
		
		log.info("[CalendarController] calendarDto : {} ", calendarDto );
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "일정 수정 성공", calendarService.updateCalendar(calendarDto)));
	}
		
	@DeleteMapping("/calendarView/{calendarCode}")
	public ResponseEntity<ResponseDto> deleteCalendar (@PathVariable Long calendarCode) {
		
		log.info("[CalendarController] calendarCode : {}", calendarCode);
		
		calendarService.deleteCalendar(calendarCode);
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "일정 삭제 성공", null));
	}

	
}