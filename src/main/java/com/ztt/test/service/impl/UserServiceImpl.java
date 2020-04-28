package com.ztt.test.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.ztt.test.entity.User;
import com.ztt.test.mapper.UserMapper;
import com.ztt.test.service.UserService;

@Service
public class UserServiceImpl implements UserService{

	private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
	
	@Autowired
	private UserMapper userMapper;
	
	@CachePut(value = "user", key = "#user.id")
	@Override
	public int saveOrUpdate(User user) {
		log.info("进入 saveOrUpdate 方法");
		return userMapper.insert(user);
	}

	@Cacheable(value = "user", key = "#id")
	@Override
	public User get(Long id) {
		log.info("进入 get 方法");
		return userMapper.findByUserId(id);
	}
	
	@CacheEvict(value = "user", key = "#id")
	@Override
	public void delete(Long id) {
		log.info("进入 delete 方法");
		userMapper.deleteByUserId(id);
	}
	
	@CacheEvict(value = "user", key = "#id")
	@Override
	public int update(User user) {
		log.info("进入 update 方法");
		return userMapper.update(user);
	}

}
