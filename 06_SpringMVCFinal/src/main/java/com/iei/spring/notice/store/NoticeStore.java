package com.iei.spring.notice.store;

import java.util.List;

import com.iei.spring.notice.domain.Notice;
import com.iei.spring.notice.domain.Search;

public interface NoticeStore {
	public List<Notice> selectAll();
	public List<Notice> selectSearchAll(Search search);
	public Notice selecttOne(int nId);
	public int insertNotice(Notice notice);
	public int updateNotice(Notice notice);
	public int deleteNotice(int nId);
}
