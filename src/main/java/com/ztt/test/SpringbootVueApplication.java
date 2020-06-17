package com.ztt.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.TaskScheduler;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.web.bind.annotation.RestController;

import com.battcn.swagger.annotation.EnableSwagger2Doc;



@RestController
@SpringBootApplication
@EnableCaching
@EnableSwagger2Doc
@EnableScheduling
@EnableAsync
//@EnableAdminServer
public class SpringbootVueApplication {
//http://localhost:8080/dev/swagger-ui.html
	public static void main(String[] args) {
		SpringApplication.run(SpringbootVueApplication.class, args);
	}
	

	@Bean
    public TaskScheduler taskScheduler() {
        ThreadPoolTaskScheduler taskScheduler = new ThreadPoolTaskScheduler();
        taskScheduler.setPoolSize(10);
        return taskScheduler;
    }
}
