package com.ztt.test;

import static org.junit.Assert.assertEquals;

import java.net.URL;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

@SpringBootTest
class SpringbootVueApplicationTests {
	
	@LocalServerPort
	private int port;
	private URL base;
	
	@Autowired
	private TestRestTemplate template;
	
	public void setUp() throws Exception {
		this.base = new URL("http://localhost:"+port+"/springbootVue/demo1");
	}
	@Test
	public void demo1() {
		ResponseEntity<String> entity = template.getForEntity(base.toString(), String.class);
		assertEquals(entity.getBody(), "Hello ztt");
	}

}
