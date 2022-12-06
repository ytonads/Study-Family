package com.greedy.StudyFamily.calendar.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.calendar.entity.Calendar;
import com.greedy.StudyFamily.subject.dto.DepartmentDto;
import com.greedy.StudyFamily.subject.entity.Department;

public interface CalendarRepository extends JpaRepository<Calendar, Long> {

	//Optional<Calendar> findByCalendarCode(Long calendarCode);
	
	//List<Department> findByLogin(Department department);
	
	Calendar findByCalendarCode(Long calendarCode);

	List<Calendar> findBydepartment(Department department);

	//List<Calendar> findByCalendar(Calendar calendar);
	
	//List<Calendar> findAll();
	
}
