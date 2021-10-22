package com.iei.spring.member.store;

import com.iei.spring.member.domain.Member;

public interface MemberStore {

	public Member selectMember(Member memberOne);
	public int checkIdDup(String userId);
	public int insertMember(Member member);
	public int updateMember(Member member);
	public int deleteMember(String userId);
}
