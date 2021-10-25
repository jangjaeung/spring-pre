package com.iei.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
				HttpSession session = request.getSession();
				session.setAttribute("loginUser", loginUser);
				return "home";
			}
		}catch(Exception e) {
			request.setAttribute("msg", e.toString());
			return "common/errorPage";
		}
		return "home";
	}
	
	//로그아웃
	@RequestMapping(value="/logout.kh", method=RequestMethod.GET)
	public String memberLogout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		if(session != null) {
			session.invalidate();
			return "home";
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
	@RequestMapping(value="memberRegister.kh", method=RequestMethod.POST)
	public String memberRegister(HttpServletRequest request) {
		String memberId = request.getParameter("user-id");
		String memberPwd = request.getParameter("user-pwd");
		String memberName = request.getParameter("user-name");
		String memberEmail = request.getParameter("user-email");
		String memberPhone = request.getParameter("user-phone");
		String memberAddr = request.getParameter("user-addr");
		Member member = new Member(memberId, memberPwd, memberName, memberEmail, memberPhone, memberAddr);
		
		try {
			int result = service.registerMember(member);
			if(result > 0) {
				return "home";
			}else {
				request.setAttribute("msg", "회원가입 실패");
				return "common/errorPage";
			}
		}catch(Exception e) {
			request.setAttribute("msg", e.toString());
			return "common/errorPage";
		}
	}
}
