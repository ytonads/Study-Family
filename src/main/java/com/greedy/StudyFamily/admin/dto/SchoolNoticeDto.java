package com.greedy.StudyFamily.admin.dto;

import java.sql.Date;

import com.greedy.StudyFamily.student.dto.DepartmentDto;

import lombok.Data;

@Data
public class SchoolNoticeDto {

	private Long schoolNoticeCode;
	private String schoolNoticeTitle;
	private String schoolNoticeContent;
	private Date schoolNoticeRegDate;
	private Date schoolNoticeUpdDate;
	private Date schoolNoticeDelDate;
	private String schoolNoticeState;
	private String schoolNoticeCategory;
	private DepartmentDto departmentCode;
	
}
