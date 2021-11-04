package com.iei.spring.board.store.logic;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.iei.spring.board.domain.Board;
import com.iei.spring.board.domain.PageInfo;
import com.iei.spring.board.domain.Reply;
import com.iei.spring.board.store.BoardStore;

@Repository
public class BoardStoreLogic implements BoardStore{
	@Autowired
	private SqlSession session;
	
	@Override
	public int selectListCount() {
		int count = session.selectOne("boardMapper.selectListCount");
		return count;
	}

	@Override
	public List<Board> selectAll(PageInfo pi) {
		//ROWNUM~~BETWEEN 1 AND 10
		/**
		 * RowBounds는 쿼리문을 변경하지 않고도 페이징을 처리할수 있게 해주는 클래스
		 * RowBounds의 동작은 offset값과 limit값을 이용해서 동작함
		 * offset값은 변하는 값이고 limit값은 고정값
		 * limit값이 한 페이지당 보여주고 싶은 게시물의 갯수이고
		 * offset은 건너뛰어야 할 값임
		 * ex)limit 10, offset 0, 1~10,
		 * 					   10, 11~20
		 * offset값은 currentPage에 의해서 변경됨
		 */
		int offset = (pi.getCurrentPage() -1) * pi.getBoardLimit();
		RowBounds rowBounds = new RowBounds(offset,pi.getBoardLimit());
		List<Board> bList = session.selectList("boardMapper.selectAllList", pi, rowBounds);
		return bList;
	}

	@Override
	public int addReadCount(int boardNo) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Board printOne(int boardNo) {
		Board board = session.selectOne("boardMapper.selectOneBoard",boardNo);
		return board;
	}

	@Override
	public int insertBoard(Board board) {
		int result = session.insert("boardMapper.insertBoard",board);
		return result;
	}

	@Override
	public int updateBoard(Board board) {
		int result = session.update("boardMapper.updateBoard",board);
		return result;
	}

	@Override
	public int deleteBoard(int boardNo) {
		int result = session.delete("boardMapper.deleteBoard",boardNo);
		return result;
	}

	@Override
	public List<Reply> printAllReply(int boardNo) {
		List<Reply> rList = session.selectList("boardMapper.selectReplyList", boardNo);
		return rList;
	}

	@Override
	public int insertReply(Reply reply) {
		int result = session.insert("boardMapper.insertReply",reply);
		return result;
	}

	@Override
	public int updateReply(Reply reply) {
		int result = session.update("boardMapper.updateReply",reply);
		return result;
	}

	@Override
	public int deleteReply(Reply reply) {
		int result = session.update("boardMapper.deleteReply",reply);
		return result;
	}

}
