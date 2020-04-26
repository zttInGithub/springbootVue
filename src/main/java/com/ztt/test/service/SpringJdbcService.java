package com.ztt.test.service;

import java.util.List;

import com.ztt.test.entity.User;

public interface SpringJdbcService {

	/**
	 * 查询
	 * */
	List<User> queryUsers();

}
