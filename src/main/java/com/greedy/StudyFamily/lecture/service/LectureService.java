package com.greedy.StudyFamily.lecture.service;

import java.io.IOException;
import java.util.List;
import java.util.UUID;

import javax.transaction.Transactional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.greedy.StudyFamily.admin.dto.FileDto;
import com.greedy.StudyFamily.admin.dto.LoginDto;
import com.greedy.StudyFamily.admin.entity.File;
import com.greedy.StudyFamily.admin.repository.FileRepository;
import com.greedy.StudyFamily.admin.repository.LoginRepository;
import com.greedy.StudyFamily.lecture.dto.AppClassDto;
import com.greedy.StudyFamily.lecture.dto.CourseHistoryDto;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.CourseHistory;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.repository.CourseRepository;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;
import com.greedy.StudyFamily.professor.repository.ProfessorRepository;
import com.greedy.StudyFamily.student.repository.StudentRepository;
import com.greedy.StudyFamily.util.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LectureService {

	private final CourseRepository courseRepository;
	private final LoginRepository loginRepository;
	private final FileRepository fileRepository;
	private final ProfessorRepository professorRepository;
	private final LectureRepository lectureRepository;
	private final StudentRepository studentRepository;
	private final ModelMapper modelMapper;
	
	
	@Value("${file.file-dir}")
	private String FILE_DIR;
	@Value("${file.file-url}")
	private String FILE_URL;
	
	
	public LectureService(CourseRepository courseRepository, LoginRepository loginRepository, FileRepository fileRepository, ProfessorRepository professorRepository, 
			LectureRepository lectureRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
		this.professorRepository = professorRepository;
		this.lectureRepository = lectureRepository;
		this.studentRepository = studentRepository;
		this.fileRepository = fileRepository;
		this.loginRepository = loginRepository;
		this.courseRepository = courseRepository;
		this.modelMapper = modelMapper;
	}
	
	
	
	/* ?????? ?????? ??????(??????) - ??????!!! */
	public List<LectureDto> selectLectureStuList(LoginDto loginStu) {
		
		List<Lecture> lectList = lectureRepository.findByStu(loginStu.getStudent().getStudentNo());
		List<LectureDto> lecDtoList = lectList.stream().map(lecture -> modelMapper.map(lecture, LectureDto.class)).toList();
		
		return lecDtoList;
	}
	
	

	/* ?????? ?????? ??????(??????) - ??????!!! */
	public List<LectureDto> selectLectureProList(LoginDto loginPro) {
		
		List<Lecture> lecProList = lectureRepository.findByProfessor(loginPro.getProfessor().getProfessorCode());
		List<LectureDto> lecProDtoList = lecProList.stream().map(lecture -> modelMapper.map(lecture, LectureDto.class)).toList();
		
		return lecProDtoList;
	}



	/* ?????? ?????? ??????(??????) - ??????!!! */
	public LectureDto selectLectureDetailStu(Long lectureCode) {
		
		LectureDto lectureDto = modelMapper.map(lectureRepository.findByLectureCode(lectureCode), LectureDto.class);
		
		return lectureDto;
	}

	
	/* ?????? ?????? ?????? - (??????) - ??????!!! */
	public LectureDto selectLectureDetailPro(Long lectureCode) {
		
		LectureDto lectureDto = modelMapper.map(lectureRepository.findByLectureCode(lectureCode), LectureDto.class);
		
		return lectureDto;
	}


	/* ?????? ?????? ??????(??????) - ??????!!! */
	@Transactional
	public FileDto insertLectureFile(FileDto fileDto) {
		
		log.info("[LectureService] insertLectureFile Start =====================" );
		log.info("[LectureService] fileDto : {}", fileDto );
		
		String fileName = UUID.randomUUID().toString().replace("-", "");
		String replaceFileName = null;
		
		try {
			replaceFileName = FileUploadUtils.saveFile(FILE_DIR, fileName, fileDto.getLectureFiles());
			fileDto.setSavedRoute(replaceFileName);
		
			log.info("[ProductService] replaceFileName : {}", replaceFileName);
			
			//DB ??????
			fileRepository.save(modelMapper.map(fileDto, File.class));
		
		} catch (IOException e) {
			e.printStackTrace();
			try {					
				FileUploadUtils.deleteFile(FILE_DIR, replaceFileName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		log.info("[LectureService] insertLectureFile End =====================" );
		
		return fileDto;
	}


	

	/* ?????? ?????? ??????(??????) - ??????!!! */
	@Transactional
	public FileDto updateLectureFile(FileDto fileDto) {
		
		log.info("[LectureService] updateLectureFile Start =====================" );
		log.info("[LectureService] fileDto : {}", fileDto );
		
		String replaceFileName = null;
		
		try {
			
			File oriFiles = fileRepository.findById(fileDto.getFileCode())
					.orElseThrow(() -> new IllegalArgumentException("?????? ????????? ???????????? ????????????. fileCode=" + fileDto.getFileCode()));
			String oriFile = oriFiles.getSavedRoute();
			
			log.info("[LectureService] oriFileCode : {}", oriFiles);
			
			//????????? ?????? 
			if(fileDto.getLectureFiles() != null) {
				
				String fileName = UUID.randomUUID().toString().replace("_", "");
				
				replaceFileName = FileUploadUtils.saveFile(FILE_DIR, fileName, fileDto.getLectureFiles());
				fileDto.setSavedRoute(replaceFileName);
				
				//?????? ?????? ??????
				FileUploadUtils.deleteFile(FILE_DIR, oriFile);
			
				//??? ?????? ???
			} else {
				fileDto.setSavedRoute(oriFile);
			}
			
			//?????? ??? ?????? ??????
			oriFiles.lectureUpdate(
					fileDto.getFileCode(),
					fileDto.getOriginName(),
					fileDto.getSavedRoute(),
					fileDto.getStartDate(),
					fileDto.getEndDate(),
					fileDto.getFileType(),
					fileDto.getLectureWeek()
			);
			
			fileRepository.save(oriFiles);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			 try {
				 FileUploadUtils.deleteFile(FILE_DIR, replaceFileName);
			 } catch (IOException e1) {
				 e1.printStackTrace();
			
			}
		}
		
		log.info("[LectureService] updateLectureFile End ===========================");
		
		return fileDto;
	}


	
	/* ?????? ?????? ??????(??????) - ??????!!! */
	@Transactional
	public FileDto insertTaskFile(FileDto fileDto) {
		
		log.info("[LectureService] insertTaskFile Start =====================" );
		log.info("[LectureService] fileDto : {}", fileDto );
		
		String fileName = UUID.randomUUID().toString().replace("-", "");
		String replaceFileName = null;
		
		try {
			replaceFileName = FileUploadUtils.saveFile(FILE_DIR, fileName, fileDto.getLectureFiles());
			fileDto.setSavedRoute(replaceFileName);
		
			log.info("[ProductService] replaceFileName : {}", replaceFileName);
			
			//DB ??????
			fileRepository.save(modelMapper.map(fileDto, File.class));
		
		} catch (IOException e) {
			e.printStackTrace();
			try {					
				FileUploadUtils.deleteFile(FILE_DIR, replaceFileName);
			} catch (IOException e1) {
				e1.printStackTrace();
			}
			
		}
		
		log.info("[LectureService] insertTaskFile End =====================" );
		
		return fileDto;
	}


	/* ?????? ?????? ??????(??????) - ??????!!! */
	@Transactional
	public FileDto updateTaskFile(FileDto fileDto) {
		
		log.info("[LectureService] updateTaskFile Start =====================" );
		log.info("[LectureService] fileDto : {}", fileDto );
		
		String replaceFileName = null;
		
		try {
			
			File oriFiles = fileRepository.findById(fileDto.getFileCode())
					.orElseThrow(() -> new IllegalArgumentException("?????? ????????? ???????????? ????????????. fileCode=" + fileDto.getFileCode()));
			String oriFile = oriFiles.getSavedRoute();
			
			log.info("[LectureService] oriFileCode : {}", oriFiles);
			
			//????????? ?????? 
			if(fileDto.getLectureFiles() != null) {
				
				String fileName = UUID.randomUUID().toString().replace("_", "");
				
				replaceFileName = FileUploadUtils.saveFile(FILE_DIR, fileName, fileDto.getLectureFiles());
				fileDto.setSavedRoute(replaceFileName);
				
				//?????? ?????? ??????
				FileUploadUtils.deleteFile(FILE_DIR, oriFile);
			
				//??? ?????? ???
			} else {
				fileDto.setSavedRoute(oriFile);
			}
			
			//?????? ??? ?????? ??????
			oriFiles.taskUpdate(
					fileDto.getFileCode(),
					fileDto.getOriginName(),
					fileDto.getSavedRoute(),
					fileDto.getTask(),
					fileDto.getFileType()
			);
			
			fileRepository.save(oriFiles);
			
			
		} catch (IOException e) {
			e.printStackTrace();
			 try {
				 FileUploadUtils.deleteFile(FILE_DIR, replaceFileName);
			 } catch (IOException e1) {
				 e1.printStackTrace();
			
			}
		}
		
		log.info("[LectureService] updateTaskFile End ===========================");
		
		return fileDto;
	}

	
	
	// ???????????? ????????? ??????
	public Page<LectureDto> selectLectureList(int page) {
		
		log.info("[LectureService] selectLectureList Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("lectureCode").ascending ());
	
		Page<Lecture> lectureList = lectureRepository.findAll(pageable);
		Page<LectureDto> lectureDtoList = lectureList.map(lecture -> modelMapper.map(lecture, LectureDto.class));
		
		log.info("[LectureService] lectureDtoList : {}", lectureDtoList.getContent());
		
		log.info("[LectureService] selectLectureList End =====================" );
		
		return lectureDtoList;
	}



	/* ?????? ?????? ??????(??????) */
	@Transactional
	public Object courseHisotry(CourseHistoryDto courseHistoryDto) {
		
		courseRepository.save(modelMapper.map(courseHistoryDto, CourseHistory.class));
		
		return courseHistoryDto;
	}


	/* ?????? ?????? ??????(??????) */
	@Transactional
	public CourseHistoryDto courseHisotryUpdate(CourseHistoryDto courseHistoryDto) {
		
		CourseHistory courseUpdate = courseRepository.findById(courseHistoryDto.getCourseCode())
				.orElseThrow(() -> new IllegalArgumentException("?????? ????????? ???????????? ????????????. courseCode=" + courseHistoryDto.getCourseCode()));
		
		courseUpdate.historyUpdate(
				courseHistoryDto.getCourseCode(),
				courseHistoryDto.getCourseTime(),
				courseHistoryDto.getCourseStatus()
				);
		courseRepository.save(courseUpdate);
		
		return courseHistoryDto;
	}

	 /* ?????? - [??????] ?????? ?????? ?????? ????????? ?????? */
	public List<AppClassDto> selectStudentListByLecture(int page, LectureDto lecture) {
		
		log.info("[AppClassService] selectStudentListByLecture Start =======================");
			
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("student.studentNo").descending());
			
		//?????? ????????? ??????
		Lecture findLecture = lectureRepository.findById(lecture.getLectureCode())
				.orElseThrow(() -> new IllegalArgumentException("?????? ????????? ???????????? ????????????. lectureCode= " + lecture.getLectureCode()));
		
		log.info("[AppClassService] appClassDtoList : {}", findLecture.getAppClasses());
		
		List<AppClassDto> appClassDtoList = findLecture.getAppClasses().stream().map(appClass -> modelMapper.map(appClass, AppClassDto.class)).toList();
			
		log.info("[AppClassService] selectStudentListByLecture End =======================");
		
		return appClassDtoList;
	}
	
	/* ?????? - [??????] ?????? ????????? ??????????????? ?????? ?????? */
//	@Transactional
//	public AppClassDto insertLectureEval(AppClassDto appClassDto) {
//	
//		AppClass oriEval = appClassRepository.findByLectureCode(appClassDto)
//				.orElseThrow( () -> new IllegalArgumentException("?????? ????????? ????????????."));
//		
//		oriEval.insertEval(appClassDto.getEval().getEvalCode(),
//				           appClassDto.getEval().getEvalGrade(),
//				           appClassDto.getEval().getEvalResult(),
//				           appClassDto.getEval().getEvalMiddle(),
//				           appClassDto.getEval().getEvalFinal(),
//				           appClassDto.getEval().getEvalTask(),
//				           appClassDto.getEval().getEvalAttend());
//		
//		appClassRepository.save(oriEval);
//		
//		return appClassDto;
//	}
	
}
