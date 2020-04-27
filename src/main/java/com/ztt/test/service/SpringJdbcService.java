package com.ztt.test.service;

import java.util.List;

import com.ztt.test.entity.User;

public interface SpringJdbcService {

	/**
	 * 查询
	 * */
	List<User> queryUsers();

	User getUser(Long id);

	int delUser(Long id);

	int addUser(User user);

	int editUser(Long id, User user);

}
