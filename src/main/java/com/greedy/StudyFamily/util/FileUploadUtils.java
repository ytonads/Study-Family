package com.greedy.StudyFamily.util;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartFile;

public class FileUploadUtils {

	//파일을 저장할 수 있는 메소드 선언 - INSERT
	public static String saveFile(String uploadDir, String fileName, MultipartFile multipartFile) throws IOException {
		
		//파일을 올리는 경로를 Path라는 객체로 만들었다.
		Path uploadPath = Paths.get(uploadDir);
		
		if(!Files.exists(uploadPath)) {
			Files.createDirectories(uploadPath);	//파일 경로가 없다면 만들어라~
		}
												//사용자가 업로드한 오리지날 파일에서 확장자를 뽑아오기
		String replaceFileName = fileName + "." + FilenameUtils.getExtension(multipartFile.getOriginalFilename());
								//파일명 + . + 확장자
		
		try(InputStream inputStream = multipartFile.getInputStream()){
			Path filePath = uploadPath.resolve(replaceFileName);	//파일을 어떤 경로 copy해 올건지 작성
			Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
		} catch (IOException e) {
			throw new IOException("파일을 저장하지 못하였습니다. file name : " + fileName);
		}
			//db에 저장하기 위해서 파일의 이름을 return 한다.
		return replaceFileName;
		
	}

	
	//파일을 삭제할 수 있는 메소드 선언 - DELETE
	public static void deleteFile(String uploadDir, String fileName) throws IOException {
		
		Path uploadPath = Paths.get(uploadDir);			//파일이 업로드되어있는 경로를 불러온다.
		Path filePath = uploadPath.resolve(fileName);	//업로드경로와 파일 이름을 비교한다.
		try {
			Files.delete(filePath);
		} catch (IOException e) {
			throw new IOException("파일을 삭제하지 못하였습니다. file name : " + fileName);
		}
		
		
	}
	
	
}
