package org.kh.spring.member.service.logic;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.kh.spring.member.domain.User;
import org.kh.spring.member.service.UserService;
import org.kh.spring.member.store.UserStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserStore store;
	
	@Override
	public User loginCheck(User user) {
		User userOne = null;
		try {
		Connection conn = this.getConnection();
		userOne = store.selectOneUSer(conn, user);
		conn.close();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return userOne;
	}

	
	public Connection getConnection() {
		Connection conn = null;
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "MYBATIS", "MYBATIS");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return conn;
	}
}
