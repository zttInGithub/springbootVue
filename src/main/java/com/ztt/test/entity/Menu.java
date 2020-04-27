package com.ztt.test.entity;

import java.io.Serializable;

import javax.persistence.Table;

@Table(name = "t_menu")
public class Menu implements Serializable{
	private static final long serialVersionUID = 8655851615465363473L;
	private Long id;
	private String menuName;
	private String menuPath;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getMenuName() {
		return menuName;
	}
	public void setMenuName(String menuName) {
		this.menuName = menuName;
	}
	public String getMenuPath() {
		return menuPath;
	}
	public void setMenuPath(String menuPath) {
		this.menuPath = menuPath;
	}
	@Override
	public String toString() {
		return "Menu [id=" + id + ", menuName=" + menuName + ", menuPath=" + menuPath + "]";
	}
	public Menu(Long id, String menuName, String menuPath) {
		super();
		this.id = id;
		this.menuName = menuName;
		this.menuPath = menuPath;
	}
	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
