package org.kh.spring.member.store;

import java.sql.Connection;

import org.kh.spring.member.domain.User;

public interface UserStore {
	public User selectOneUSer(Connection conn,User user);
}
