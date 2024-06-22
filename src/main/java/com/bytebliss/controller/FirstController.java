package com.bytebliss.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FirstController {
	
	@GetMapping("/greeting")
	public String sayHello() {
		return "Hello! Welcome to ByteBliss";
	}
	
	@GetMapping("/sayHello")
	public String sayHellooo() {
		return "Say Hello";
	}

}
