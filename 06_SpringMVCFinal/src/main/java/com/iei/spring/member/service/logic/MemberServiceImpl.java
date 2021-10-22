package com.iei.spring.member.service.logic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iei.spring.member.domain.Member;
import com.iei.spring.member.service.MemberService;
import com.iei.spring.member.store.MemberStore;

@Service
public class MemberServiceImpl implements MemberService{
	
	@Autowired
	private MemberStore store; 
	
	@Override
	public Member loginMember(Member memberOne) {
		Member mOne = store.selectMember(memberOne);
		return mOne;
	}

	@Override
	public int checkIdDup(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int registerMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int modifyMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int removeMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
