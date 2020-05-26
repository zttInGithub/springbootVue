package com.ztt.test.entity;


import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.ztt.test.groups.Groups;

public class School {
	
	@NotNull(message="id,不能为空",groups=Groups.Update.class)
	private Integer id;
	
	@NotBlank(message = "name 不允许为空", groups = Groups.Default.class)
	private String name;
	
//	@NotNull(message = "price 不允许为空", groups = Groups.Default.class)
//	private BigDecimal price;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
//	public BigDecimal getPrice() {
//		return price;
//	}
//	public void setPrice(BigDecimal price) {
//		this.price = price;
//	}
	
}
