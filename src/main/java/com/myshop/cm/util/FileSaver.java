package com.myshop.cm.util;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Calendar;
import java.util.UUID;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.multipart.MultipartFile;

@Component
public class FileSaver {
	
	//file Delete
	public boolean fileDelete(String realPath, String fileName) throws Exception {
		File file = new File(realPath, fileName);
		boolean check = false;
		
		if(file.exists()) {
			check = file.delete();
		}
		return check;
	}
	
	//3. IO Stream 사용
	public String save3(String realPath, MultipartFile multipartFile) throws Exception {
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		String fileName = UUID.randomUUID().toString();
		fileName = fileName+ "_"+multipartFile.getOriginalFilename();
		
		file = new File(realPath, fileName);
		
		FileOutputStream fo = new FileOutputStream(file);
		fo.write(multipartFile.getBytes());
		fo.close();
		
		return fileName;
	}
	
	//2. MultipartFile 객체의 transferTO 메서드 사용
	public String save2(String realPath, MultipartFile multipartFile) throws Exception {
		File file = new File(realPath);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		//저장할 파일명
		String fileName = UUID.randomUUID().toString();
		fileName = fileName+ "_"+multipartFile.getOriginalFilename();
		//System.out.println(fileName);
		
		file = new File(realPath, fileName);
		
		multipartFile.transferTo(file);
		
		return fileName;
	}
	
	//1. Spring에서 제공하는 FileCopyUtils 클래스의 copy 메서드 사용
	public String save(String realPath, MultipartFile multipartFile) throws Exception {
		System.out.println(realPath);
		File file = new File(realPath);
		//System.out.println(file.exists());
		if(!file.exists()) {
			file.mkdirs();
			//mkdir()은 해당 폴더만 만들어주나 mkdirs는 해당 폴더까지의 폴더 전체를 없는 것들 모두 만들어줌
		}
		//System.out.println(file.exists());
		//System.out.println(file.isDirectory()); //폴더입니까? true면 폴더, false면 file
		
		Calendar ca = Calendar.getInstance();
		Long name = ca.getTimeInMillis();
		int index = multipartFile.getOriginalFilename().lastIndexOf(".");
	
		//System.out.println(a);
		String fileName = name+multipartFile.getOriginalFilename().substring(index);
		//System.out.println(sub);
		
		file = new File(realPath, fileName); //폴더,파일명
		//System.out.println(String.valueOf(name)+sub);
		FileCopyUtils.copy(multipartFile.getBytes(), file); //꺼내올 파일, 저장할 파일
	
		return fileName;
	}
}