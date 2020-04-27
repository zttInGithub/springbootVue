package com.ztt.test.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ztt.test.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
	List<Role> findAllByRoleName(String roleName);
}
