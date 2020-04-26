package com.ztt.test.dao.impl;

import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import com.ztt.test.dao.springJdbcDao;
import com.ztt.test.entity.User;

@Component
public class springJdbcDaoImpl implements springJdbcDao {

	private final JdbcTemplate JdbcTemplate;
	
	public springJdbcDaoImpl(JdbcTemplate jdbcTemplate) {
		this.JdbcTemplate = jdbcTemplate;
	}
	@Override
	public List<User> queryUsers() {
		String sql = "select u.* from t_user";
		return JdbcTemplate.query(sql, new Object[] {}, new BeanPropertyRowMapper<>(User.class));
	}

}
