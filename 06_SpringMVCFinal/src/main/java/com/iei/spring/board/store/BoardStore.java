package com.iei.spring.board.store;

import java.util.List;

import com.iei.spring.board.domain.Board;
import com.iei.spring.board.domain.PageInfo;
import com.iei.spring.board.domain.Reply;

public interface BoardStore {
	public int selectListCount();
	public List<Board> selectAll(PageInfo pi);
	public int addReadCount(int boardNo);
	public Board printOne(int boardNo);
	public int insertBoard(Board board);
	public int updateBoard(Board board);
	public int deleteBoard(int boardNo);
	
	public List<Reply> printAllReply(int boardNo);
	public int insertReply(Reply reply);
	public int updateReply(Reply reply);
	public int deleteReply(Reply reply);
}
