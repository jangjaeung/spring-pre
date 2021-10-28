package com.iei.spring.notice.store;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iei.spring.notice.domain.Notice;
import com.iei.spring.notice.domain.Search;

@Repository
public class NoticeStoreLogic implements NoticeStore{
	
	@Autowired
	private SqlSessionTemplate sqlSession;
	
	@Override
	public List<Notice> selectAll() {
		List<Notice> nList = sqlSession.selectList("noticeMapper.selectNoticeList");
		return nList;
	}

	@Override
	public List<Notice> selectSearchAll(Search search) {
		List<Notice> searchList = sqlSession.selectList("noticeMapper.selectSearchList",search);
		return searchList;
	}

	@Override
	public Notice selectOne(int nId) {
		Notice notice = sqlSession.selectOne("noticeMapper.selectOneNotice",nId);
		return notice;
	}

	@Override
	public int insertNotice(Notice notice) {
		int result = sqlSession.insert("noticeMapper.insertNotice",notice);
		return result;
	}

	@Override
	public int updateNotice(Notice notice) {
		int result = sqlSession.update("noticeMapper.updateNotice",notice);
		return result;
	}

	@Override
	public int deleteNotice(int nId) {
		int result = sqlSession.delete("noticeMapper.deleteNotice",nId);
		return result;
	}

}
