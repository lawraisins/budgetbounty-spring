package com.hcltech;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MyController {
	@RequestMapping("/hello")
	public String myMethod() {
		return "Welcome to Spring Boot World";
	}
	
	@RequestMapping("/hi")
	public String myMethod2() {
		return "Welcome Jack";
	}
	

}
