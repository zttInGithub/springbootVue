package com.ztt.test.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ztt.test.dao.SpringJdbcDao;
import com.ztt.test.entity.User;

@Component
public class springJdbcDaoImpl implements SpringJdbcDao {

	private final JdbcTemplate JdbcTemplate;
	
	public springJdbcDaoImpl(JdbcTemplate jdbcTemplate) {
		this.JdbcTemplate = jdbcTemplate;
	}
	@Override
	public List<User> queryUsers() {
		String sql = "select u.* from t_user u";
		return JdbcTemplate.query(sql, new Object[] {}, new BeanPropertyRowMapper<>(User.class));
	}
	@Override
	public User getUser(Long id) {
		String sql = " select u.* from t_user u where u.id = ?";
		return JdbcTemplate.queryForObject(sql, new Object[] {id},new BeanPropertyRowMapper<>(User.class) );
	}
	@Override
	public int delUser(Long id) {
		String sql = " delete from t_user where id = ?";
		return JdbcTemplate.update(sql,id);
	}
	@Override
	public int addUser(User user) {
		String sql = " insert into t_user(username,`password`) values(?,?)";
		return JdbcTemplate.update(sql, user.getUsername(),user.getPassword());
	}
	@Override
	public int edit(Long id, User user) {
		String sql = " update t_user set username = ? ,`password` = ? where id = ?";
		return JdbcTemplate.update(sql, user.getUsername(),user.getPassword(), id);
	}

}
