package com.ztt.test.dao;

import java.util.List;

import com.ztt.test.entity.User;

public interface SpringJdbcDao {

	List<User> queryUsers();

	User getUser(Long id);

	int delUser(Long id);

	int addUser(User user);

	int edit(Long id, User user);

	
}
