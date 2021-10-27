package com.iei.spring.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.iei.spring.notice.domain.Notice;
import com.iei.spring.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="noticeList.kh",method=RequestMethod.GET)
	public String showNoticeList(Model model) {
		List<Notice> nList = service.printAll();
		if(!nList.isEmpty()) {
			model.addAttribute("nList",nList);
			return "notice/noticeListView";
		}else {
			model.addAttribute("msg","공지사항조회실패");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="noticeWriteView.kh", method=RequestMethod.GET)
	public String noticeWriteView() {
		return "notice/noticeWriteForm";
	}
	
	@RequestMapping(value="noticeRegister.kh",method=RequestMethod.POST)
	public String registerNotice(
			@ModelAttribute Notice notice,//jsp파일 폼태그 안에 name값들은 vo에 변수명과 맞추기
			@RequestParam(value="uploadFile", required=false) MultipartFile uploadFile,
			HttpServletRequest request,//파일 업로드할때 씀
			Model model) {
		// value값은 jsp의 input태그의 name값이고
		// required=false는 파일이 필수가 아님을 알려주는 것이고
		// MultipartFile은  MultipartResolver 객체를 빈으로 등록해서 사용한다는 것이다.
		if(!uploadFile.getOriginalFilename().equals("")) {
			//uploadFile이 비어있지 않으면
			String filePath = saveFile(uploadFile, request);
			if(filePath != null) {
				notice.setNoticeFilePath(filePath);
			}
		}
		int result = service.registerNotice(notice);
		if(result > 0) {
			return "redirect:home.kh";
		}else {
			model.addAttribute("msg","공지사항 등록 실패");
			return "common/errorPage";
		}
	}
	public String saveFile(MultipartFile file, HttpServletRequest request) {
		//파일저장 경로 설정
		String root = request.getSession().getServletContext().getRealPath("resources");
		//저장폴더 선택
		String savePath = root + "\\nuploadFiles"; // \\는 폴더와 폴더사이 구분
		//없으면 생성
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdir(); //폴더생성
		}
		String filePath = folder + "\\" + file.getOriginalFilename();
		//파일 저장
		try {
			file.transferTo(new File(filePath)); //파일저장
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//파일 경로 리턴
		return filePath;
	}
}
