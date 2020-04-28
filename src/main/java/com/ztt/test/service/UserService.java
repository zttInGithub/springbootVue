package com.ztt.test.service;

import com.ztt.test.entity.User;

public interface UserService {
	
	int saveOrUpdate(User user);
	
	User get(Long id);
	
	void delete(Long id);
	
	int update(User user);
}
