package com.ztt.test.controller;

import javax.security.sasl.AuthorizeCallback;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ztt.test.entity.Author;

@Controller
@RequestMapping
public class ThymeleafController {
	
	@GetMapping("index")
	public ModelAndView index() {
		ModelAndView view = new ModelAndView();
	   //设置跳转的视图 默认映射到 src/main/resources/templates/{viewName}.html
		view.setViewName("index");
		//设置属性
		view.addObject("title", "我的web页面");
		view.addObject("desc", "springbootVue");
		Author author = new Author();
		author.setAge(22);
		author.setName("ztt");
		author.setEmail("269941971@qq.com");
		view.addObject("author", author);
		return view;
	}
	
	@GetMapping("/index1")
	public String index1(HttpServletRequest request) {
		request.setAttribute("title", "我的web页面1");
		request.setAttribute("desc", "springbootVue1");
		Author author = new Author();
		author.setAge(23);
		author.setEmail("269941971@qq.com");
		author.setName("ztt");
		request.setAttribute("author", author);
		//返回的 index 默认映射到 src/main/resources/templates/xxxx.html
		return "index";
	}
}
