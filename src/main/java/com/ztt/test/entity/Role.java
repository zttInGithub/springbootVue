package com.ztt.test.entity;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity(name = "t_role")
public class Role implements Serializable{
	private static final long seialVersionUID = 8655851615465363473L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String unlevel;
	private String roleName;
	
	@Transient
	private String status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUnlevel() {
		return unlevel;
	}

	public void setUnlevel(String unlevel) {
		this.unlevel = unlevel;
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", unlevel=" + unlevel + ", roleName=" + roleName + ", status=" + status + "]";
	}

	public Role(String unlevel, String roleName) {
		super();
		this.unlevel = unlevel;
		this.roleName = roleName;
	}

	public Role(Long id, String unlevel, String roleName, String status) {
		super();
		this.id = id;
		this.unlevel = unlevel;
		this.roleName = roleName;
		this.status = status;
	}

	public Role(Long id, String unlevel, String roleName) {
		super();
		this.id = id;
		this.unlevel = unlevel;
		this.roleName = roleName;
	}

	public Role() {
		super();
	}
	
}
