package com.greedy.StudyFamily.lecture.service;

import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.exception.UserNotFoundException;
import com.greedy.StudyFamily.lecture.dto.AppClassDto;
import com.greedy.StudyFamily.lecture.dto.AppClassesDto;
import com.greedy.StudyFamily.lecture.dto.EvalDto;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.AppClass;
import com.greedy.StudyFamily.lecture.entity.AppClassWrite;
import com.greedy.StudyFamily.lecture.entity.Eval;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.repository.AppClassRepository;
import com.greedy.StudyFamily.lecture.repository.EvalRepository;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;
import com.greedy.StudyFamily.student.entity.Student;
import com.greedy.StudyFamily.student.repository.StudentRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class AppClassService {
	
	private final EvalRepository evalRepository;
	private final AppClassRepository appClassRepository;
	private final LectureRepository lectureRepository;
	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;
	
	
	public AppClassService(EvalRepository evalRepository, AppClassRepository appClassRepository, LectureRepository lectureRepository, 
			StudentRepository studentRepository, ModelMapper modelMapper) {
		this.appClassRepository = appClassRepository;
		this.lectureRepository = lectureRepository;
		this.studentRepository = studentRepository;
		this.evalRepository = evalRepository;
		this.modelMapper = modelMapper;
	}

	//수강신청
	@Transactional
	public AppClassDto insertAppClass(AppClassesDto appClassDto) {
		log.info("[AppClassService] insertAppClass Start =========================");
		log.info("[AppClassService] appClassDto : {}", appClassDto);
		log.info("[AppClassService] appClassDto : {}", appClassDto.getLecture().getLectureCode());
		
		AppClassWrite appClasswrite = new AppClassWrite();
		appClasswrite.setLectureCode(appClassDto.getLecture().getLectureCode());
		appClasswrite.setStudentNo(appClassDto.getStudent().getStudentNo());
		// 신청학생 정보 입력
		AppClassWrite appClass = appClassRepository.save(appClasswrite);
		
		// Lecture 테이블의 Lecture 조회하여 신청인원 업데이트
		Lecture foundLecture = lectureRepository.findByLecture(appClassDto.getLecture().getLectureCode())
				.orElseThrow(() -> new IllegalArgumentException("해당 강좌가 없습니다. lectureCode=" + appClassDto.getLecture().getLectureCode()));
		
		//수강신청하면 신청인원 카운팅
		foundLecture.setLecturePersonnel(foundLecture.getLecturePersonnel() + 1);
		
		if(foundLecture.getLecturePersonnel() > foundLecture.getCapacity()) throw new RuntimeException("신청 인원 초과입니다.");
		
		log.info("[AppClassService] insertAppClass End =========================");
		
		return modelMapper.map(appClass, AppClassDto.class);
	}
	
	 //수강취소
	@Transactional
	public void deleteAppClass(Lecture lectureCode) {
		
		log.info("[AppClassService] deleteAppClass Start =========================");
		log.info("[AppClassService] appClassDto : {}", lectureCode);

		AppClass foundlecture = (AppClass) appClassRepository.findByLecture(lectureCode).get();

		//수강취소하면 신청인원 카운팅
		Student student = studentRepository.findById(foundlecture.getStudent().getStudentNo()).get();
	    student.cancel(foundlecture);
	    Lecture lecture = lectureRepository.findById(foundlecture.getLecture().getLectureCode()).get();
	    lecture.cancel();
	    
		appClassRepository.delete(foundlecture);
		
		log.info("[AppClassService] deleteAppClass End =========================");
		
	}
	 
	 
	// 수강신청한 리스트 조회
	  public List<AppClassDto> selectAppClassList(Long studentNo) { 
	  log.info("[AppClassService] selectAppClassList Start =========================");
	  log.info("[AppClassService] studentNo : {}", studentNo);
	  
	  Student student = studentRepository.findByStudentNo(studentNo)
	  .orElseThrow(() -> new UserNotFoundException("해당 학생이 없습니다."));
	  
	  List<AppClassDto> appClassList = appClassRepository.findByStudent(student, Sort.by(Sort.Direction.DESC, "appClassCode")) 
			  .stream().map(appClass -> modelMapper.map(appClass, AppClassDto.class)).collect(Collectors.toList());
	  
	  log.info("[AppClassService] appClassList : {}", appClassList);
	  log.info("[AppClassService] selectAppClassList End =========================");
	  
	  return appClassList; 
	  }
	  
	  
	  

	  /* 태익 - [교수] 해당 강좌 학생 리스트 조회 */
		public List<AppClassDto> selectStudentListByLecture(int page, LectureDto lecture) {
			
			log.info("[AppClassService] selectStudentListByLecture Start =======================");
				
			Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("student.studentNo").descending());
				
			//강좌 엔티티 조회
			Lecture findLecture = lectureRepository.findById(lecture.getLectureCode())
					.orElseThrow(() -> new IllegalArgumentException("해당 강좌가 존재하지 않습니다. lectureCode= " + lecture.getLectureCode()));
			
			log.info("[AppClassService] appClassDtoList : {}", findLecture.getAppClasses());
			
			List<AppClassDto> appClassDtoList = findLecture.getAppClasses().stream().map(appClass -> modelMapper.map(appClass, AppClassDto.class)).toList();
				
			log.info("[AppClassService] selectStudentListByLecture End =======================");
			
			return appClassDtoList;
		}
		
		/* 태익 - [교수] 학생 리스트 페이지에서 강좌 평가 */
		@Transactional
		public EvalDto insertLectureEval(EvalDto evalDto) {
		
			Eval updateEval = evalRepository.findById(evalDto.getEvalCode())
					.orElseThrow(() -> new RuntimeException("존재하지 않는 평가입니다."));
			
			updateEval.setEvalMiddle(evalDto.getEvalMiddle());
			updateEval.setEvalFinal(evalDto.getEvalFinal());
			updateEval.setEvalTask(evalDto.getEvalTask());
			updateEval.setEvalAttend(evalDto.getEvalAttend());
			
			long sum = 0;
			sum = sum + evalDto.getEvalMiddle().longValue() + evalDto.getEvalFinal().longValue() + evalDto.getEvalTask().longValue() + evalDto.getEvalAttend().longValue();
			updateEval.setEvalResult(sum);
			evalRepository.save(updateEval);
			
			if(sum >= 90) {
				updateEval.setEvalGrade("A");
			} else if(sum < 90 && sum >= 80) {
				updateEval.setEvalGrade("B");
			} else if(sum < 80 && sum >= 70) {
				updateEval.setEvalGrade("C");
			} else if(sum < 70 && sum >= 60) {
				updateEval.setEvalGrade("D");
			} else {
				updateEval.setEvalGrade("F");
			}
			
//			if(sum >= 380) {
//				updateEval.setEvalGrade("A+");
//			} else if(sum < 380 && sum >= 360) {
//				updateEval.setEvalGrade("A0");
//			} else if(sum < 360 && sum >= 340) {
//				updateEval.setEvalGrade("A-");
//			} else if(sum < 340 && sum >= 320) {
//				updateEval.setEvalGrade("B+");
//			} else if(sum < 320 && sum >= 300) {
//				updateEval.setEvalGrade("B0");
//			} else if(sum < 300 && sum >= 280) {
//				updateEval.setEvalGrade("B-");
//			} else if(sum < 280 && sum >= 260) {
//				updateEval.setEvalGrade("C+");
//			} else if(sum < 260 && sum >= 240) {
//				updateEval.setEvalGrade("C0");
//			} else if(sum < 240 && sum >= 220) {
//				updateEval.setEvalGrade("C-");
//			} else if(sum < 220 && sum >= 200) {
//				updateEval.setEvalGrade("D+");
//			} else if(sum < 200 && sum >= 180) {
//				updateEval.setEvalGrade("D0");
//			} else if(sum < 180 && sum >= 160) {
//				updateEval.setEvalGrade("D-");
//			} else {
//				updateEval.setEvalGrade("F");
//			}
			
			return evalDto;
		}
	  
}
