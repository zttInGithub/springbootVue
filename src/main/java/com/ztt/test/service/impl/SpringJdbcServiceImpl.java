package com.ztt.test.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.ztt.test.dao.springJdbcDao;
import com.ztt.test.entity.User;
import com.ztt.test.service.SpringJdbcService;

@Component
public class SpringJdbcServiceImpl implements SpringJdbcService {

	@Autowired
	private springJdbcDao springJdbcDaoImpl;
	
	@Override
	public List<User> queryUsers() {
		return springJdbcDaoImpl.queryUsers();
	}

}
