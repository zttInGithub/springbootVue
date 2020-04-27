package com.ztt.test.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.ztt.test.entity.User;

public interface UserMapper {
	
//	 t_user 操作：演示两种方式
//	 第一种是基于mybatis3.x版本后提供的注解方式
//	 第二种是早期写法，将SQL写在 XML 中
	@Select("select * from t_user where username = #{username}")
	List<User> findByUsername(@Param("username") String username);
	
	int insert(User user);
}
