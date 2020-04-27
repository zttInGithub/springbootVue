package com.ztt.test.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@GetMapping("/{id}")
	public User getUser(@PathVariable Long id) {
		System.out.println(id);
		return springJdbcServiceImpl.getUser(id);
	}
	
	@DeleteMapping("/{id}")
	public int delUser(@PathVariable Long id) {
		System.out.println(id);
		return springJdbcServiceImpl.delUser(id);
	}
	
	@PostMapping()
	public int addUser(@RequestBody User user) {
		System.out.println(user.toString());
		return springJdbcServiceImpl.addUser(user);
	}
	
	@PutMapping("/{id}")
	public int editUser(@PathVariable Long id,@RequestBody User user) {
		System.out.println(id+user.toString());
		return springJdbcServiceImpl.editUser(id,user);
	}
}
