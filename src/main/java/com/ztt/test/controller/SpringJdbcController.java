package com.ztt.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ztt.test.entity.User;
import com.ztt.test.service.SpringJdbcService;
import org.springframework.web.bind.annotation.RestController;

//@Component
@RestController
@RequestMapping("/users")
public class SpringJdbcController {
	
	@Autowired
	private SpringJdbcService springJdbcServiceImpl;
	
	@GetMapping
	public List<User> queryUsers(){
		System.out.println(springJdbcServiceImpl.queryUsers().toString());
		return springJdbcServiceImpl.queryUsers();
	}
}
