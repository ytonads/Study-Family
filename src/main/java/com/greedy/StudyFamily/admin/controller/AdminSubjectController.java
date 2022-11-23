package com.greedy.StudyFamily.admin.controller;

import javax.transaction.Transactional;

import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


import com.greedy.StudyFamily.admin.service.AdminSubjectService;
import com.greedy.StudyFamily.common.ResponseDto;
import com.greedy.StudyFamily.common.paging.Pagenation;
import com.greedy.StudyFamily.common.paging.PagingButtonInfo;
import com.greedy.StudyFamily.common.paging.ResponseDtoWithPaging;
import com.greedy.StudyFamily.subject.dto.SubjectDto;
import com.greedy.StudyFamily.subject.entity.Subject;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("admin/subject")
public class AdminSubjectController {

	private final AdminSubjectService subjectService; 
	
	public AdminSubjectController(AdminSubjectService subjectService) {
		this.subjectService = subjectService;
	}
	
	/* <관리자> 과목 조회(과목코드기준)*/
	@GetMapping("code")
	public ResponseEntity<ResponseDto> subCodeSubjectList(@RequestParam(name="page", defaultValue="1") int page){
		
		log.info("[SubjectController] ============================ Start ");
		
		Page<SubjectDto> subjectList = subjectService.subCodeSubjectList(page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(subjectList);

		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(subjectList.getContent());
		
		log.info("[SubjectController] ============================ End ");

		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}

	
	/* <관리자> 과목 조회(과목명기준)*/
	@GetMapping("title")
	public ResponseEntity<ResponseDto> subTitleCodeSubjectList(@RequestParam(name="page", defaultValue="1") int page){
		
		log.info("[SubjectController] ============================ Start ");
		
		Page<SubjectDto> subjectList = subjectService.subTitleCodeSubjectList(page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(subjectList);

		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(subjectList.getContent());
		
		log.info("[SubjectController] ============================ End ");

		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
	
	
	/* <관리자> 과목 조회(학과코드기준)*/
	@GetMapping("department")
	public ResponseEntity<ResponseDto> departmentSubjectList(@RequestParam(name="page", defaultValue="1") int page){
		
		log.info("[SubjectController] ============================ Start ");
		
		Page<SubjectDto> subjectList = subjectService.departmentSubjectList(page);
		PagingButtonInfo pageInfo = Pagenation.getPagingButtonInfo(subjectList);

		ResponseDtoWithPaging responseDtoWithPaging = new ResponseDtoWithPaging();
		responseDtoWithPaging.setPageInfo(pageInfo);
		responseDtoWithPaging.setData(subjectList.getContent());
		
		log.info("[SubjectController] ============================ End ");

		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "조회 성공", responseDtoWithPaging));
	}
		
	/* <관리자> 과목 등록 */
	@Transactional
	@PostMapping("regis")
	public ResponseEntity<ResponseDto> insertSubjectList(@RequestBody SubjectDto subjectDto){
	
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "등록 성공",subjectService.insertSubject(subjectDto) ));
	}
	
	
	/* <관리자> 과목 수정 */
	@Transactional
	@PostMapping("detail/modify")
	public ResponseEntity<ResponseDto> modifySubjectList(@RequestBody SubjectDto subjectDto){
	
			
		
		return ResponseEntity.ok().body(new ResponseDto(HttpStatus.OK, "수정 성공",subjectService.modifySubject(subjectDto)));
	}
	
	/* <관리자> 과목 삭제*/
	@DeleteMapping("detail")
	public ResponseEntity<String> deleteSubjectList(@RequestBody SubjectDto subjectDto){
			
		
		subjectService.deleteSubject(subjectDto);
		
		return ResponseEntity.ok("삭제 완료");
				
	}
	
	
	
}
