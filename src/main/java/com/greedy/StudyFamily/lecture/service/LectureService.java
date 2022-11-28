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
import com.greedy.StudyFamily.admin.entity.Login;
import com.greedy.StudyFamily.admin.repository.FileRepository;
import com.greedy.StudyFamily.admin.repository.LoginRepository;
import com.greedy.StudyFamily.lecture.dto.LectureDto;
import com.greedy.StudyFamily.lecture.entity.Lecture;
import com.greedy.StudyFamily.lecture.repository.LectureRepository;
import com.greedy.StudyFamily.professor.dto.ProfessorDto;
import com.greedy.StudyFamily.professor.entity.Professor;
import com.greedy.StudyFamily.professor.repository.ProfessorRepository;
import com.greedy.StudyFamily.student.dto.StudentDto;
import com.greedy.StudyFamily.student.entity.Student;
import com.greedy.StudyFamily.student.repository.StudentRepository;
import com.greedy.StudyFamily.util.FileUploadUtils;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class LectureService {

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
	
	
	public LectureService(LoginRepository loginRepository, FileRepository fileRepository, ProfessorRepository professorRepository, 
			LectureRepository lectureRepository, StudentRepository studentRepository, ModelMapper modelMapper) {
		this.professorRepository = professorRepository;
		this.lectureRepository = lectureRepository;
		this.studentRepository = studentRepository;
		this.fileRepository = fileRepository;
		this.loginRepository = loginRepository;
		this.modelMapper = modelMapper;
	}
	
	
	
	/* 강좌 목록 조회(학생) - 완료!!! */
	public List<LectureDto> selectLectureStuList(LoginDto loginStu) {
		
		List<Lecture> lectList = lectureRepository.findByStu(loginStu.getStudent().getStudentNo());
		List<LectureDto> lecDtoList = lectList.stream().map(lecture -> modelMapper.map(lecture, LectureDto.class)).toList();
		
		return lecDtoList;
	}
	
	

	/* 강좌 목록 조회(교수) - 완료!!! */
	public List<LectureDto> selectLectureProList(LoginDto loginPro) {
		
		List<Lecture> lecProList = lectureRepository.findByProfessor(loginPro.getProfessor().getProfessorCode());
		List<LectureDto> lecProDtoList = lecProList.stream().map(lecture -> modelMapper.map(lecture, LectureDto.class)).toList();
		
		return lecProDtoList;
	}



	/* 강좌 상세 조회(학생) - 완료!!! */
	public LectureDto selectLectureDetailStu(Long lectureCode) {
		
		LectureDto lectureDto = modelMapper.map(lectureRepository.findByLectureCode(lectureCode), LectureDto.class);
		
		return lectureDto;
	}

	
	/* 강좌 상세 조회 - (교수) - 완료!!! */
	public LectureDto selectLectureDetailPro(Long lectureCode) {
		
		LectureDto lectureDto = modelMapper.map(lectureRepository.findByLectureCode(lectureCode), LectureDto.class);
		
		return lectureDto;
	}

	

	/* 강좌 상세 조회 - (교수) - 완료!!! */
//	public LectureDto selectLectureDetailPro(Long lectureCode, Long professorCode) {
//		
//		log.info("[LectureService] selectLectureDetailPro Start =====================" );
//		log.info("[LectureService] lectureCode : {}", lectureCode );
//		log.info("[LectureService] professorCode : {}", professorCode );
//		
//		LectureDto lectureDto = modelMapper.map(lectureRepository.findByLectureCode(lectureCode), LectureDto.class);
//		
//		log.info("[LectureService] lectureCode : {}", lectureCode );
//		log.info("[LectureService] selectLectureDetailPro End =====================" );
//		
//		
//		return lectureDto;
//	}


	/* 수업 자료 등록(교수) - 완료!!! */
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
			
			//DB 저장
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


	

	/* 수업 자료 수정(교수) - 완료!!! */
	@Transactional
	public FileDto updateLectureFile(FileDto fileDto) {
		
		log.info("[LectureService] updateLectureFile Start =====================" );
		log.info("[LectureService] fileDto : {}", fileDto );
		
		String replaceFileName = null;
		
		try {
			
			File oriFiles = fileRepository.findById(fileDto.getFileCode())
					.orElseThrow(() -> new IllegalArgumentException("해당 자료가 존재하지 않습니다. fileCode=" + fileDto.getFileCode()));
			String oriFile = oriFiles.getSavedRoute();
			
			log.info("[LectureService] oriFileCode : {}", oriFiles);
			
			//이미지 변경 
			if(fileDto.getLectureFiles() != null) {
				
				String fileName = UUID.randomUUID().toString().replace("_", "");
				
				replaceFileName = FileUploadUtils.saveFile(FILE_DIR, fileName, fileDto.getLectureFiles());
				fileDto.setSavedRoute(replaceFileName);
				
				//기존 파일 삭제
				FileUploadUtils.deleteFile(FILE_DIR, oriFile);
			
				//미 변경 시
			} else {
				fileDto.setSavedRoute(oriFile);
			}
			
			//파일 외 내용 수정
			oriFiles.lectureUpdate(
					fileDto.getFileCode(),
					fileDto.getOriginName(),
					fileDto.getSavedRoute(),
//					fileDto.getStartDate(),
//					fileDto.getEndDate(),
					fileDto.getFileType(),
					fileDto.getLectureWeekCode()
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


	
	/* 과제 파일 등록(학생) - 완료!!! */
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
			
			//DB 저장
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


	/* 과제 파일 수정(학생) - 완료!!! */
	@Transactional
	public FileDto updateTaskFile(FileDto fileDto) {
		
		log.info("[LectureService] updateTaskFile Start =====================" );
		log.info("[LectureService] fileDto : {}", fileDto );
		
		String replaceFileName = null;
		
		try {
			
			File oriFiles = fileRepository.findById(fileDto.getFileCode())
					.orElseThrow(() -> new IllegalArgumentException("해당 자료가 존재하지 않습니다. fileCode=" + fileDto.getFileCode()));
			String oriFile = oriFiles.getSavedRoute();
			
			log.info("[LectureService] oriFileCode : {}", oriFiles);
			
			//이미지 변경 
			if(fileDto.getLectureFiles() != null) {
				
				String fileName = UUID.randomUUID().toString().replace("_", "");
				
				replaceFileName = FileUploadUtils.saveFile(FILE_DIR, fileName, fileDto.getLectureFiles());
				fileDto.setSavedRoute(replaceFileName);
				
				//기존 파일 삭제
				FileUploadUtils.deleteFile(FILE_DIR, oriFile);
			
				//미 변경 시
			} else {
				fileDto.setSavedRoute(oriFile);
			}
			
			//파일 외 내용 수정
			oriFiles.taskUpdate(
					fileDto.getFileCode(),
					fileDto.getOriginName(),
					fileDto.getSavedRoute(),
					fileDto.getTaskCode(),
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

	
	
	// 수강신청 리스트 조회
	public Page<LectureDto> selectLectureList(int page) {
		
		log.info("[LectureService] selectLectureList Start =====================" );
		
		Pageable pageable = PageRequest.of(page - 1, 10, Sort.by("lectureCode").ascending ());
	
		Page<Lecture> lectureList = lectureRepository.findAll(pageable);
		Page<LectureDto> lectureDtoList = lectureList.map(lecture -> modelMapper.map(lecture, LectureDto.class));
		
		log.info("[LectureService] lectureDtoList : {}", lectureDtoList.getContent());
		
		log.info("[LectureService] selectLectureList End =====================" );
		
		return lectureDtoList;
	}



	

	





	

	
	
}
