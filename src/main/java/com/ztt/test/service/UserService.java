package com.ztt.test.service;

import com.ztt.test.entity.User;

public interface UserService {
	
	User saveOrUpdate(User user);
	
	User get(Long id);
	
	void delete(Long id);
	
}
