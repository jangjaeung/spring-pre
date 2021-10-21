package org.kh.spring.member.controller;

import javax.servlet.http.HttpServletRequest;

import org.kh.spring.member.domain.User;
import org.kh.spring.member.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController {
	
	@Autowired
	private UserService service;
	
	@RequestMapping(value="/login.do", method=RequestMethod.POST)
	public String memberLogin(HttpServletRequest request) {
		
		String userId = request.getParameter("user-id");
		String userPwd = request.getParameter("user-pw");
		User user = new User();
		
		user.setUserId(userId);
		user.setUserPwd(userPwd);
		User result = service.loginCheck(user);
		
		if(result != null) {
			return "member/loginSuccess";
		}else {
			return "member/loginFaild";
		}
	}
}
