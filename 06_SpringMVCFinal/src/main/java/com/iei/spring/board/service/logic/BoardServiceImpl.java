package com.iei.spring.board.service.logic;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.iei.spring.board.domain.Board;
import com.iei.spring.board.domain.PageInfo;
import com.iei.spring.board.domain.Reply;
import com.iei.spring.board.service.BoardService;
import com.iei.spring.board.store.BoardStore;
@Service
public class BoardServiceImpl implements BoardService{
	
	@Autowired
	private BoardStore store;
	
	@Override
	public int getListCount() {
		int totalCount = store.selectListCount();
		return totalCount;
	}

	@Override
	public List<Board> printAll(PageInfo pi) {
		List<Board> bList = store.selectAll(pi);
		return bList;
	}

	@Override
	public int addReadCount(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Board printOne(int boardNo) {
		Board board = store.printOne(boardNo);
		return board;
	}

	@Override
	public int registerBoard(Board board) {
		int result = store.insertBoard(board);
		return result;
	}

	@Override
	public int modifyBoard(Board board) {
		int result = store.updateBoard(board);
		return result;
	}

	@Override
	public int removeBoard(int boardNo) {
		int result = store.deleteBoard(boardNo);
		return result;
	}

	@Override
	public List<Reply> printAllReply(int boardNo) {
		List<Reply> rList = store.printAllReply(boardNo);
		return rList;
	}

	@Override
	public int registerReply(Reply reply) {
		int result = store.insertReply(reply);
		return result;
	}

	@Override
	public int modifyReply(Reply reply) {
		int result = store.updateReply(reply);
		return result;
	}

	@Override
	public int removeReply(Reply reply) {
		int result = store.deleteReply(reply);
		return result;
	}

}
