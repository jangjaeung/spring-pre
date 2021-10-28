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
import com.iei.spring.notice.domain.Search;
import com.iei.spring.notice.service.NoticeService;

@Controller
public class NoticeController {
	
	@Autowired
	private NoticeService service;
	
	@RequestMapping(value="noticeList.kh",method=RequestMethod.GET)
	public String showNoticeList(Model model) {
		try {
			List<Notice> nList = service.printAll();
			if(!nList.isEmpty()) {
				model.addAttribute("nList",nList);
			}else {
				model.addAttribute("nList",null);
			}
			return "notice/noticeListView";
		}catch(Exception e) {
			model.addAttribute("msg","공지사항 조회 실패");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="noticeDetail.kh", method=RequestMethod.GET)
	public String noticeDetail(@RequestParam("noticeNo") int nId, Model model) {
		Notice notice = service.printOne(nId);
		if(notice != null) {
			model.addAttribute("notice",notice);
			return "notice/noticeDetailView";
		}else {
			model.addAttribute("msg","공지사항 상세조회실패");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="noticeSearch.kh",method=RequestMethod.GET)
	public String noticeSearchList(@ModelAttribute Search search,Model model) {
		List<Notice> searchList = service.printSearchAll(search);
		if(!searchList.isEmpty()) {
			model.addAttribute("nList",searchList);
			model.addAttribute("search",search);
			return "notice/noticeListView";
		}else {
			model.addAttribute("msg","검색실패");
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
				notice.setNoticeFilePath(uploadFile.getOriginalFilename());
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
	
	@RequestMapping(value="noticeModifyView.kh",method=RequestMethod.GET)
	public String noticeModify(@RequestParam("noticeNo") int nId,Model model) {
		Notice notice = service.printOne(nId);
		model.addAttribute("notice",notice);
		return "notice/noticeUpdateView";
	}
	
	@RequestMapping(value="noticeUpdate.kh",method=RequestMethod.POST)
	public String noticeUpdate(@ModelAttribute Notice notice,Model model,HttpServletRequest request,@RequestParam("reloadFile") MultipartFile reloadFile) {
		//수정은 업로드된 파일이 있을 경우
		if(reloadFile != null && !reloadFile.isEmpty()) {
			if(notice.getNoticeFilePath() != null) {
				//삭제하고
				deleteFile(notice.getNoticeFilePath(), request);
			}
			//다시업로드
			String savePath = saveFile(reloadFile,request);
			if(savePath != null) {
				notice.setNoticeFilePath(reloadFile.getOriginalFilename());
			}
		}
		
		//DB데이터 수정
		int result = service.modifyNotice(notice);
		if(result > 0) {
			return "redirect:noticeDetail.kh?noticeNo="+notice.getNoticeNo();
		}else {
			model.addAttribute("msg","수정실패");
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="noticeDelete.kh",method=RequestMethod.GET)
	public String noticeDelete(@RequestParam("noticeNo") int nId,Model model,HttpServletRequest request) {
		Notice notice = service.printOne(nId);
		int result = service.removeNotice(nId);
		if(result>0) {
			if(notice.getNoticeFilePath() != null) {
				deleteFile(notice.getNoticeFilePath(),request);
			}
			return "redirect:noticeList.kh";
		}else {
			model.addAttribute("msg","삭제실패");
			return "common/errorPage";
		}
	}
	public void deleteFile(String fileName, HttpServletRequest request) {
		String root = request.getSession().getServletContext().getRealPath("resources");
		String deletePath = root + "\\nuploadFiles";
		File deleteFile = new File(deletePath + "\\" + fileName);
		if(deleteFile.exists()) {
			deleteFile.delete();//파일삭제
		}
	}
}
