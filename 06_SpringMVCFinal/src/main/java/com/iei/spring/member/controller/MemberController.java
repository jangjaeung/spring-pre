package com.iei.spring.member.controller;

import javax.servlet.http.HttpServletRequest;
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
}
