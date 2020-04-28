package com.ztt.test.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.ztt.test.entity.Menu;

import tk.mybatis.mapper.common.BaseMapper;

@Mapper
public interface MenuMapper extends BaseMapper<Menu>{
	
	int countByUsername(String menuname);
}
