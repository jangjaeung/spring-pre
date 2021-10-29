package com.iei.spring.board.controller;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
import org.springframework.web.servlet.ModelAndView;

import com.iei.spring.board.domain.Board;
import com.iei.spring.board.domain.PageInfo;
import com.iei.spring.board.service.BoardService;
import com.iei.spring.common.Pagination;

@Controller
public class BoardController {
	
	@Autowired
	private BoardService service;
	
	@RequestMapping(value="boardList.kh",method=RequestMethod.GET)
	public ModelAndView boardListView(
			ModelAndView mv,
			@RequestParam(value="page",required=false)Integer page) {
		int currentPage = (page!=null) ? page : 1;
		int totalCount = service.getListCount();
		PageInfo pi = Pagination.getPageInfo(currentPage, totalCount);
		List<Board> bList = service.printAll(pi);
		if(!bList.isEmpty()) {
			mv.addObject("bList",bList);
			mv.addObject("pi",pi);
//			return "board/boardListView";
			mv.setViewName("board/boardListView");
		}else {
			mv.addObject("msg","게시글 전체조회 실패");
			mv.setViewName("common/errorPage");
		}
		return mv;
	}
	
	@RequestMapping(value="boardWriteView.kh",method=RequestMethod.GET)
	public String boardWriterView() {
		return "board/boardWriteForm";
	}
	
	@RequestMapping(value="boardRegister.kh",method=RequestMethod.POST)
	public String registerBoard(
			Model model,
			@ModelAttribute Board board,
			@RequestParam(value="uploadFile",required=false) MultipartFile uploadFile,
			HttpServletRequest request) {
		//required 필수값설정 false는 아님
		//1. 폼데이터에서 보내주는 데이터를 메소드 파라미터로 받으세요(글, 파일)
		//2. 서버(프로젝트 워크스페이스)에 파일을 저장하는 작업을 하세요
		// - 서버에 파일을 저장할때 saveFile 메소드를 사용하여 저장
		if(!uploadFile.getOriginalFilename().equals("")) {
			//업로드파일이 비어있지 않으면
			String renameFileName = saveFile(uploadFile,request);//매개변수확인
			if(renameFileName != null) {
				board.setBoardFileName(uploadFile.getOriginalFilename());
				board.setBoardFileRename(renameFileName);
			}
		}
		//3. db에 데이터를 저장하는 작업
		int result = service.registerBoard(board);
		//4. 성공하면 boardList로 이동시키고 실패하면 error페이지로 이동
		if(result>0) {
			return "redirect:boardList.kh";
		}else {
			model.addAttribute("msg","게시판등록실패");
			return "common/errorPage";
		}
		//5. 어노테이션을 잊지말고 꼭 적어주세요(총6개)
	}
	//renameFileName = saveFile의 매개변수
	public String saveFile(MultipartFile uploadFile,HttpServletRequest request) {
		//파일저장 경로 설정
		String root = request.getSession().getServletContext().getRealPath("resources");
		//저장폴더 선택
		String savePath = root + "\\buploadFiles"; // \\는 폴더와 폴더사이 구분
		//없으면 생성
		File folder = new File(savePath);
		if(!folder.exists()) {
			folder.mkdir(); //폴더생성
		}
		//파일명 변경하기
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
		String originalFileName = uploadFile.getOriginalFilename();
		String renameFileName = sdf.format(new Date(System.currentTimeMillis()))+"."+originalFileName.substring(originalFileName.lastIndexOf(".")+1);
		String filePath = folder + "\\" + renameFileName;
		//파일 저장
		try {
			uploadFile.transferTo(new File(filePath)); //파일저장
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		//파일 경로 리턴
		return renameFileName;
	}
}
