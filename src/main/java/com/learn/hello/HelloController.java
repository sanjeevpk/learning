package com.learn.hello;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
	
	@RequestMapping("/hello")
	public String sayHi(){
		return "Hiiiii";
	}
	
	@RequestMapping("/company/hello")
	public String sayHello(){
		return "Hello";
	}
}
