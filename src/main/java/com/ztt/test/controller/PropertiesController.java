package com.ztt.test.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ztt.test.properties.MyProperties;
import com.ztt.test.properties.MyProperties1;

@RequestMapping("/properties")
@RestController
public class PropertiesController {
	
	private static final Logger log = LoggerFactory.getLogger(PropertiesController.class);
	@Autowired
	private MyProperties myProperties;
	@Autowired
	private MyProperties1 myProperties1;
	
	
//	@Autowired 
//	public PropertiesController(MyProperties myProperties) {
//		this.myProperties = myProperties; 
//	}
	 
	
	@GetMapping("/1")
	public MyProperties myProperties() {
		log.info("++++++++++++++++++++++++++++++++++++++++++");
		log.info(myProperties.toString());
		log.info("==========================================");
		return myProperties;
	}
	@GetMapping("/2")
	public MyProperties1 myProperties1() {
		log.info("++++++++++++++++++++++++++++++++++++++++++");
		log.info(myProperties1.toString());
		log.info("==========================================");
		return myProperties1;
	}
}
