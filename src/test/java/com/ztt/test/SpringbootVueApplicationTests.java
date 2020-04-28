package com.ztt.test;



import java.io.Serializable;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.IntStream;

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
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.ztt.test.dao.RoleRepository;
import com.ztt.test.entity.Menu;
import com.ztt.test.entity.Role;
import com.ztt.test.entity.User;
import com.ztt.test.mapper.MenuMapper;
import com.ztt.test.mapper.UserMapper;
import com.ztt.test.service.UserService;


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
	
	@Autowired
	private MenuMapper menuMapper;
	
	@Autowired
	private UserService userService;

	@Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate<String, Serializable> redisCacheTemplate;

    /**
     * cache-redis
     */
    @Test
    public void test6() {
        final int user = userService.saveOrUpdate(new User(7L, "u5", "p5"));
        try {
        	log.info("[saveOrUpdate] - [{}]", user);
            final User user1 = userService.get(7L);
            log.info("[get] - [{}]", user1);
            userService.delete(7L);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    /**
     * Lettuce Redis
     */
    @Test
    public void test5() {
        // TODO 测试线程安全
        ExecutorService executorService = Executors.newFixedThreadPool(1000);
        //opsForValue--->https://blog.csdn.net/qq_25135655/article/details/80357137
        IntStream.range(0, 1000).forEach(i ->
                executorService.execute(() -> stringRedisTemplate.opsForValue().increment("kk", 1))
        );
        stringRedisTemplate.opsForValue().set("k1", "v1");
        final String k1 = stringRedisTemplate.opsForValue().get("k1");
        log.info("[字符缓存结果] - [{}]", k1);
        // TODO 以下只演示整合，具体Redis命令可以参考官方文档，Spring Data Redis 只是改了个名字而已，Redis支持的命令它都支持
        String key = "ztt:user:1";
        redisCacheTemplate.opsForValue().set(key, new User(1L, "u1", "pa"));
        // TODO 对应 String（字符串）
        final User user = (User) redisCacheTemplate.opsForValue().get(key);
        log.info("[对象缓存结果] - [{}]", user);
    }
    
    /**
     * mybatis分页
     * @throws Exception
     */
	@Test
    public void test4() throws Exception {
        final Menu menu1 = new Menu("u1", "p1");
        final Menu menu2 = new Menu("u1", "p2");
        final Menu menu3 = new Menu("u3", "p3");
        //menuMapper.insertSelective(menu1);
        log.info("[menu1回写主键] - [{}]", menu1.getId());
      //  menuMapper.insertSelective(menu2);
        log.info("[menu2回写主键] - [{}]", menu2.getId());
      //  menuMapper.insertSelective(menu3);
        log.info("[menu3回写主键] - [{}]", menu3.getId());
        final int count = menuMapper.countByUsername("u1");
        log.info("[调用自己写的SQL] - [{}]", count);

        // TODO 模拟分页
        menuMapper.insertSelective(new Menu("u1", "p1"));
        menuMapper.insertSelective(new Menu("u2", "p2"));
        menuMapper.insertSelective(new Menu("u3", "p3"));
        menuMapper.insertSelective(new Menu("u4", "p4"));
        menuMapper.insertSelective(new Menu("u5", "p5"));
        menuMapper.insertSelective(new Menu("u6", "p6"));
        menuMapper.insertSelective(new Menu("u7", "p7"));
        menuMapper.insertSelective(new Menu("u8", "p8"));
        menuMapper.insertSelective(new Menu("u9", "p9"));
        menuMapper.insertSelective(new Menu("u10", "p10"));
        // TODO 分页 + 排序 this.menuMapper.selectAll() 这一句就是我们需要写的查询，有了这两款插件无缝切换各种数据库
        final PageInfo<Object> pageInfo = PageHelper.startPage(1, 10).setOrderBy("id desc").doSelectPageInfo(() -> this.menuMapper.selectAll());
        log.info("[lambda写法] - [分页信息] - [{}]", pageInfo.toString());

        PageHelper.startPage(1, 10).setOrderBy("id desc");
        final PageInfo<Menu> menuPageInfo = new PageInfo<>(this.menuMapper.selectAll());
        log.info("[普通写法] - [{}]", menuPageInfo);
    }
	
	/**
	 * mybtis
	 * */
    @Test
    public void test3() throws Exception {
        final int row1 = userMapper.insert(new User("u1", "p1"));
        log.info("[添加结果] - [{}]", row1);
        final int row2 = userMapper.insert(new User("u2", "p2"));
        log.info("[添加结果] - [{}]", row2);
        final int row3 = userMapper.insert(new User("u1", "p3"));
        log.info("[添加结果] - [{}]", row3);
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
