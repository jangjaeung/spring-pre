package org.kh.spring.member.store.logic;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.kh.spring.member.domain.User;
import org.kh.spring.member.store.UserStore;
import org.springframework.stereotype.Repository;

@Repository
public class UserStoreLogic implements UserStore{

	@Override
	public User selectOneUSer(Connection conn, User user) {
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		User uOne = null;
		String query = "SELECT * FROM USER_INFORMATION WHERE USER_ID = ? AND USER_PWD = ?";
		
		try {
			pstmt = conn.prepareStatement(query);
			pstmt.setString(1, user.getUserId());
			pstmt.setString(2, user.getUserPwd());
			rset = pstmt.executeQuery();
			if(rset.next()) {
				uOne = new User();
				uOne.setUserNo(rset.getInt("USER_NO"));
				uOne.setUserId(rset.getString("USER_ID"));
				uOne.setUserPwd(rset.getString("USER_PWD"));
				uOne.setUserName(rset.getString("USER_NAME"));
				uOne.setUserAddr(rset.getString("USER_ADDR"));
				uOne.setRegDate(rset.getDate("REG_DATE"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				pstmt.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return uOne;
	}

}
