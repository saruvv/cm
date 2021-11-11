package com.myshop.cm.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

import com.myshop.cm.model.FilesVO;

@Component
public class FileDown extends AbstractView{
//따로 이름이 없으니 클래스명의 첫글자를 소문자로 바꾼 값을 이름으로 지정.
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
											//Model을 모아놓은 Map
			HttpServletResponse response) throws Exception {
		// TODO Auto-generated method stub
		//System.out.println("FileDown Class");
		
		FilesVO filesVO = (FilesVO)model.get("file");
	
		String board = (String)model.get("board");
		//System.out.println(filesVO.getFname());
		//System.out.println(filesVO.getOname());
		//System.out.println(board);
		
		String realPath = request.getSession().getServletContext().getRealPath("resources/upload/"+board);
		System.out.println(realPath);
		
		File file = new File(realPath, filesVO.getFname());
		
		//한글파일명 처리
		response.setCharacterEncoding("UTF-8");
		
		//파일의 크기-다운 시간 얼마 남았습니다 알려주기 위해
		response.setContentLength((int)file.length());
		
		//다운로드시 파일 이름 인코딩
		String fileName = URLEncoder.encode(filesVO.getOname(), "UTF-8");
		
		//header 설정
		response.setHeader("Content-disposition", "attachment;filename=\""+fileName+"\"");
		response.setHeader("Content-transfer-Encoding", "binary");
		
		//outputStream
		//먼저 파일을 읽어와야 해
		FileInputStream fi = new FileInputStream(file);
		OutputStream os = response.getOutputStream();
		
		FileCopyUtils.copy(fi, os);
		
		os.close();
		fi.close();
	}

	
}