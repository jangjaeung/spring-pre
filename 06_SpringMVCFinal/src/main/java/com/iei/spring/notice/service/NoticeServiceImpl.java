package com.iei.spring.notice.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iei.spring.notice.domain.Notice;
import com.iei.spring.notice.domain.Search;
import com.iei.spring.notice.store.NoticeStore;

@Service
public class NoticeServiceImpl implements NoticeService{
	
	@Autowired
	private NoticeStore store;
	
	@Override
	public List<Notice> printAll() {
		List<Notice> nList = store.selectAll();
		return nList;
	}

	@Override
	public List<Notice> printSearchAll(Search search) {
		List<Notice> searchList = store.selectSearchAll(search);
		return searchList;
	}

	@Override
	public Notice printOne(int nId) {
		Notice notice = store.selectOne(nId);
		return notice;
	}

	@Override
	public int registerNotice(Notice notice) {
		int result = store.insertNotice(notice);
		return result;
	}

	@Override
	public int modifyNotice(Notice notice) {
		int result = store.updateNotice(notice);
		return result;
	}

	@Override
	public int removeNotice(int nId) {
		int result = store.deleteNotice(nId);
		return result;
	}

}
