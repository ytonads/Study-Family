package com.greedy.StudyFamily.calendar.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.calendar.dto.CalendarDto;
import com.greedy.StudyFamily.calendar.entity.Calendar;
import com.greedy.StudyFamily.calendar.repository.CalendarRepository;
import com.greedy.StudyFamily.subject.dto.DepartmentDto;
import com.greedy.StudyFamily.subject.entity.Department;
import com.greedy.StudyFamily.subject.repository.DepartmentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CalendarService {

	private final CalendarRepository calendarRepository;
	private final DepartmentRepository departmentRepository;
	private final ModelMapper modelMapper;
	
	public CalendarService(CalendarRepository calendarRepository, DepartmentRepository departmentRepository, ModelMapper modelMapper) {
		this.calendarRepository = calendarRepository;
		this.departmentRepository = departmentRepository;
		this.modelMapper = modelMapper;
	}
	
	public List<CalendarDto> showingCalendar(DepartmentDto departmentDto) {
		
		log.info("[CalendarService] departmentDto : {} ", departmentDto);
		
		List<Calendar> showingCalendar = calendarRepository.findBydepartment(modelMapper.map(departmentDto, Department.class));
	
		return showingCalendar.stream().map(calendar -> modelMapper.map(calendar, CalendarDto.class)).collect(Collectors.toList());
	
	}
	
	public CalendarDto selectCalendar(Long calendarCode) {
		
		Calendar calendar = calendarRepository.findByCalendarCode(calendarCode);
		
		calendar.setCalendarContent((calendar.getCalendarContent()).substring(0, 0));
		
		return modelMapper.map(calendar, CalendarDto.class);
		
	}
	
	@Transactional
	public CalendarDto insertCalendar(CalendarDto calendarDto) {

		log.info("[CalendarService] calendarDTO : {} ", calendarDto );
		
		calendarRepository.save(modelMapper.map(calendarDto, Calendar.class));
		
		return calendarDto;
	}

	@Transactional
	public CalendarDto updateCalendar(CalendarDto calendarDto) {

		log.info("[CalendarService] calendarDto : {}", calendarDto);
		
		Calendar updatecalendar = calendarRepository.findByCalendarCode(calendarDto.getCalendarCode());
		
		updatecalendar.update(calendarDto.getCalendarContent());
		
		log.info("[CalendarService] updatecalendar : {}", updatecalendar);
		
		calendarRepository.save(updatecalendar);
		
		return calendarDto;
	}

	public CalendarDto deleteCalendar(Long calendarCode) {
		
		calendarRepository.delete(calendarRepository.findByCalendarCode(calendarCode));

		return null;
	}
	
}
