package com.iei.spring.notice.service;

import java.util.List;

import com.iei.spring.notice.domain.Notice;
import com.iei.spring.notice.domain.Search;

public interface NoticeService {
	public List<Notice> printAll();
	public List<Notice> printSearchAll(Search search);
	public Notice printOne(int nId);
	public int registerNotice(Notice notice);
	public int modifyNotice(Notice notice);
	public int removeNotice(int nId);
}
