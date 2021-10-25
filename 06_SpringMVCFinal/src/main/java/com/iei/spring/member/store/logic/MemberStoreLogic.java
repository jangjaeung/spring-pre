package com.iei.spring.member.store.logic;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iei.spring.member.domain.Member;
import com.iei.spring.member.store.MemberStore;

@Repository
public class MemberStoreLogic implements MemberStore{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public Member selectMember(Member memberOne) {
		Member mOne = sqlSession.selectOne("memberMapper.selectLoginMember",memberOne);
		return mOne;
	}

	@Override
	public int checkIdDup(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertMember(Member member) {
		int result = sqlSession.insert("memberMapper.insertMember",member);
		return result;
	}

	@Override
	public int updateMember(Member member) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int deleteMember(String userId) {
		// TODO Auto-generated method stub
		return 0;
	}

}
