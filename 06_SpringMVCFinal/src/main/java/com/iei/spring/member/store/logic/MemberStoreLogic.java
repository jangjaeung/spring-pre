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
		int result = sqlSession.selectOne("memberMapper.checkIdDup",userId);
		return result;
	}

	@Override
	public int insertMember(Member member) {
		int result = sqlSession.insert("memberMapper.insertMember",member);
		return result;
	}

	@Override
	public int updateMember(Member member) {
		int result = sqlSession.update("memberMapper.updateMember",member);
		return result;
	}

	@Override
	public int deleteMember(String userId) {
		int result = sqlSession.delete("memberMapper.deleteMember",userId);
		return result;
	}

}
