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
		int result = store.checkIdDup(userId);
		return result;
	}

	@Override
	public int registerMember(Member member) {
		int result = store.insertMember(member);
		store.insertMember(member);
		return result;
	}

	@Override
	public int modifyMember(Member member) {
		int result = store.updateMember(member);
		return result;
	}

	@Override
	public int removeMember(String userId) {
		int result = store.deleteMember(userId);
		return result;
	}

}
