package com.ztt.test;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class SpringbootVueApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootVueApplication.class, args);
	}
	
//	@GetMapping("/demo1")
//	public String demo1() {
//		return "testztt";
//	}

//	@Bean
//	public CommandLineRunner commandLineRuuner(ApplicationContext ctx) {
//		return args -> {
//			System.out.println("spring默认提供的bean");
//			String[] beanNames = ctx.getBeanDefinitionNames();
//			Arrays.sort(beanNames);
//			Arrays.stream(beanNames).forEach(System.out::println);
//		};
//	}

}
