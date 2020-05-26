package com.ztt.test.controller;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ztt.test.annotation.DateTime;
import com.ztt.test.entity.Book;
import com.ztt.test.entity.School;
import com.ztt.test.groups.Groups;

//@Validated
@RestController()
public class ValidateController {
	
	@GetMapping("/validateTest2")
    public String test2(@NotBlank(message = "name 不能为空") @Length(min = 2, max = 10, message = "name 长度必须在 {min} - {max} 之间") String name) {
        return "success";
    }

    @GetMapping("/validateTest3")
    public String test3(@Validated Book book) {
        return "success";
    }
    
    @GetMapping("/validateTest4")
    public String test(@DateTime(message = "您输入的格式错误，正确的格式为：{format}", format = "yyyy-MM-dd HH:mm") String date) {
        return "success";
    }
	
    @GetMapping("/insert")
    public String insert(@Validated(value=Groups.Default.class) School school) {
    	return "insert";
    }
    
    @GetMapping("/update")
    public String update(@Validated(value= {Groups.Default.class,Groups.Update.class}) School school) {
    	return "update";
    }
}
