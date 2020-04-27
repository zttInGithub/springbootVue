package com.ztt.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ztt.test.dao.SpringJdbcDao;
import com.ztt.test.entity.User;
import com.ztt.test.service.SpringJdbcService;

@Component
public class SpringJdbcServiceImpl implements SpringJdbcService {

	@Autowired
	private SpringJdbcDao springJdbcDaoImpl;
	
	@Override
	public List<User> queryUsers() {
		return springJdbcDaoImpl.queryUsers();
	}

	@Override
	public User getUser(Long id) {
		return springJdbcDaoImpl.getUser(id);
	}

	@Override
	public int delUser(Long id) {
		return springJdbcDaoImpl.delUser(id);
	}

	@Override
	public int addUser(User user) {
		return springJdbcDaoImpl.addUser(user);
	}

	@Override
	public int editUser(Long id, User user) {
		return springJdbcDaoImpl.edit(id,user);
	}

}
