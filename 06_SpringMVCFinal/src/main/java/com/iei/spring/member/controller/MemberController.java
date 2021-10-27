package com.iei.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.iei.spring.member.domain.Member;
import com.iei.spring.member.service.MemberService;

@Controller
public class MemberController {
	
	@Autowired
	private MemberService service;
	
	//로그인
	@RequestMapping(value="/login.kh",method=RequestMethod.POST)
	public String memberLogin(HttpServletRequest request) {
		String memberId = request.getParameter("user-id");
		String memberPwd = request.getParameter("user-pwd");
		
		//비즈니스 로직 시작
		Member memberOne = new Member();
		memberOne.setMemberId(memberId);
		memberOne.setMemberPwd(memberPwd);
		try {
			Member loginUser = service.loginMember(memberOne);
			if(loginUser != null) {
				//원래는 세션에 모든 정보를 담지 않음  아이디정도만 담지만 실습용으로 다 담고 진행
				//Member member = service.printOneById("userId");
				//request.setAttribute("memberOne"~~~ 이렇게 새로운 메소드에 작성해야함 
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				return "redirect:home.kh";
			}
		}catch(Exception e) {
			request.setAttribute("msg", e.toString());
			return "common/errorPage";
		}
		return "redirect:home.kh";
	}
	
	//로그아웃
	@RequestMapping(value="/logout.kh", method=RequestMethod.GET)
	public String memberLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
			return "redirect:home.kh";
			//redirect를 하지않을시 회원가입후 화면은 home으로 가지만 url이 바뀌어 새로고침시 오류나옴 response.sendRedirect("home.kh");와 같음
		}else {
			request.setAttribute("msg", "로그아웃 실패");
			return "common/errorPage";
		}
	}
	
	//회원가입페이지이동
	@RequestMapping(value="/enrollView.kh", method=RequestMethod.GET)
	public String enrollView(HttpServletRequest request) {
		return "member/memberJoin";
	}
	
	//회원가입
//	1. HttpServletRequest를 통해서 받기
//	2. @RequestParam 어노테이션 사용해서 받기
//	2-1. @RequestParam을 이용하여 변수 선언해서 불러올 필요 없이 어노테이션으로 가능 배열은 request.getParameterValues로 해야함
//	3. @ModelAttribute 어노테이션 사용해서 받기 *주의점 Member객체에 있는 변수 이름이 jsp파일 name값과 동일해야함 ex)user-id -> memberId
//	3-1 주의사항 VO(Domain)클래스 기본 생성자 존재
//	3-2 setter 메소드 존재
//	3-3 요청페이지(jsp)에서 name 속석명이 domain(vo) 필드명(멤버변수명)과 같아야함
//	요청페이지에서 memberAddress가 없고 post address1,2가 있으니 RequestParam을 통해 불러와서 member객체에memberAddr변수에 setter을 이용하여 합쳐서 저장한다
	@RequestMapping(value="memberRegister.kh", method=RequestMethod.POST)
	public String memberRegister(HttpServletRequest request,
			@ModelAttribute Member member,
			@RequestParam("post") String post,
			@RequestParam("address1") String address1,
			@RequestParam("address2") String address2
			/*@RequestParam("user-id") String memberId,
			@RequestParam("user-pwd") String memberPwd,
			@RequestParam("user-name") String memberName,
			@RequestParam("user-email") String memberEmail,
			@RequestParam("user-phone") String memberPhone,
			@RequestParam("user-addr") String memberAddr*/) {
//		String memberId = request.getParameter("user-id");
//		String memberPwd = request.getParameter("user-pwd");
//		String memberName = request.getParameter("user-name");
//		String memberEmail = request.getParameter("user-email");
//		String memberPhone = request.getParameter("user-phone");
//		String memberAddr = request.getParameter("user-addr");
//		Member member = new Member(memberId, memberPwd, memberName, memberEmail, memberPhone, memberAddr);
		member.setMemberAddr(post+","+address1+","+address2);
		
		try {
			int result = service.registerMember(member);
			if(result > 0) {
				return "redirect:home.kh";
				//redirect를 하지않을시 회원가입후 화면은 home으로 가지만 url이 바뀌어 새로고침시 오류나옴 response.sendRedirect("home.kh");와 같음
			}else {
				request.setAttribute("msg", "회원가입 실패");
				return "common/errorPage";
			}
		}catch(Exception e) {
			request.setAttribute("msg", e.toString());
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="myInfo.kh", method=RequestMethod.GET)
	public String myInfoView() {
		return "member/myPage";
	}
	
	@RequestMapping(value="memberModify.kh", method=RequestMethod.POST)
	public String modifyMember(@ModelAttribute Member member,
			@RequestParam("post") String post,
			@RequestParam("address1") String address1,
			@RequestParam("address2") String address2,
			Model model,
			HttpServletRequest request) {
		
		HttpSession session = request.getSession();
		member.setMemberAddr(post+","+address1+","+address2);
		try {
			int result = service.modifyMember(member);
			if(result > 0) {
				session.setAttribute("loginUser", member);
				return "redirect:home.kh";
			}else {
//				request.setAttribute("msg", "회원정보 수정 실패");
				//스프링에서만 가능 model 인터페이스
				model.addAttribute("msg","회원정보 수정 실패");
				return "common/errorPage";
			}
		}catch(Exception e) {
			return "common/errorPage";
		}
	}
	
	@RequestMapping(value="memberDelete.kh", method=RequestMethod.GET)
	public String memberDelete(
			@RequestParam("memberId") String memberId,
			Model model) {
		int result = service.removeMember(memberId);
//		HttpSession session = request.getSession();
		if(result > 0) {
//			session.invalidate();
			//세션 파괴하고 홈으로 가는 메서드가 이미 로그아웃에 있기때문에 return을 통해 로그아웃.kh로 리다이렉트 함
			return "redirect:logout.kh";
		}else {
			model.addAttribute("msg","회원탈퇴실패");
			return "common/errorPage";
		}
	}
	
		@ResponseBody
	   @RequestMapping(value="checkDupId.kh", method=RequestMethod.GET)
	   public String idDuplicateCheck(@RequestParam("memberId") String memberId) {
	      int result = service.checkIdDup(memberId);
//	      return result + "";
	      return String.valueOf(result); // /WEB-INF/views/1.jsp > 1, 0
	   }
}
