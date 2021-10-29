package com.iei.spring.board.service;

import java.util.List;

import com.iei.spring.board.domain.Board;
import com.iei.spring.board.domain.PageInfo;
import com.iei.spring.board.domain.Reply;

public interface BoardService {
	//javaodc 주석
	/**
	 * 게시물 전체 갯수
	 * @return int
	 */
	public int getListCount();
	/**
	 * 게시판 게시물 전체 조회
	 * @param pi
	 * @return List
	 */
	public List<Board> printAll(PageInfo pi);
	/**
	 * 게시물 상세 조회시 조회수 증가
	 * @param boardNo
	 * @return int
	 */
	public int addReadCount(int boardNo);
	public Board printOne(int boardNo);
	public int registerBoard(Board board);
	public int modifyBoard(Board board);
	public int removeBoard(int boardNo);
	
	public List<Reply> printAllReply(int boardNo);
	public int registerReply(Reply reply);
	public int modifyReply(Reply reply);
	public int removeReply(Reply reply);
}
