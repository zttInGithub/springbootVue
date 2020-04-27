package com.ztt.test;



import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.ztt.test.dao.RoleRepository;
import com.ztt.test.entity.Role;
import com.ztt.test.entity.User;
import com.ztt.test.mapper.UserMapper;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootVueApplication.class,webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootVueApplicationTests {
	
	private static final Logger	log = LoggerFactory.getLogger(SpringbootVueApplicationTests.class);
	
	@Autowired
	private TestRestTemplate template;
	
	@LocalServerPort
	private int port;
	
	@Autowired
    private RoleRepository rserRepository;

	@Autowired
    private UserMapper userMapper;

    @Test
    public void test3() throws Exception {
     //   final int row1 = userMapper.insert(new User("u1", "p1"));
     //   log.info("[添加结果] - [{}]", row1);
     //   final int row2 = userMapper.insert(new User("u2", "p2"));
     //   log.info("[添加结果] - [{}]", row2);
      //s  final int row3 = userMapper.insert(new User("u1", "p3"));
      //  log.info("[添加结果] - [{}]", row3);
        final List<User> u1 = userMapper.findByUsername("ztt");
        log.info("[根据用户名查询] - [{}]", u1);
    }
	/**
	 * springdatejpa
	 * */
    @Test
    public void test2() throws Exception {
        final Role role = rserRepository.save(new Role("u1", "p1"));
        log.info("[添加成功] - [{}]", role);
        final List<Role> r1 = rserRepository.findAllByRoleName("u1");
        log.info("[条件查询] - [{}]", r1);
        Pageable pageable = PageRequest.of(0, 10, Sort.by(Sort.Order.desc("roleName")));
        final Page<Role> roles = rserRepository.findAll(pageable);
        log.info("[分页+排序+查询所有] - [{}]", roles.getContent());
        rserRepository.findById(roles.getContent().get(0).getId()).ifPresent(role1 -> log.info("[主键查询] - [{}]", role1));
        final Role edit = rserRepository.save(new Role(role.getId(), "修改后的ui", "修改后的p1"));
        log.info("[修改成功] - [{}]", edit);
        rserRepository.deleteById(role.getId());
        log.info("[删除主键为 {} 成功] - [{}]", role.getId());
    }
	
	/**
	 * jdbctemplate
	 * */
	@Test
    public void test1() throws Exception {
		User user1 = new User();
		user1.setUsername("ztt3");
		user1.setPassword("321");
      //  template.postForEntity("http://localhost:" + port + "/dev/users", user1, Integer.class);
        log.info("[添加用户成功]\n");
        // TODO 如果是返回的集合,要用 exchange 而不是 getForEntity ，后者需要自己强转类型
        ResponseEntity<List<User>> response2 = template.exchange("http://localhost:" + port + "/dev/users", HttpMethod.GET, null, new ParameterizedTypeReference<List<User>>() {
        });
        final List<User> body = response2.getBody();
        log.info("[查询所有] - [{}]\n", body);
        Long userId = body.get(0).getId();
        ResponseEntity<User> response3 = template.getForEntity("http://localhost:" + port + "/dev/users/{id}", User.class, userId);
        log.info("[主键查询] - [{}]\n", response3.getBody());
        
        User user2 = new User();
		user2.setUsername("ztt3");
		user2.setPassword("321");
        
       // template.put("http://localhost:" + port + "/dev/users/{id}", user2, userId);
        log.info("[修改用户成功]\n");
       // template.delete("http://localhost:" + port + "/dev/users/{id}", userId);
        log.info("[删除用户成功]");
    }
	
}
