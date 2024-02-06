package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {
//calls the run method of the SpringApllication
//Args:class that contains the main method and args that can be passed thru the commandline
		SpringApplication.run(DemoApplication.class, args);
	}

	//map HTTP GET REQUESTS TO THE SPECIFIED URL
	@GetMapping("/hello")
	//@RequestParam indicates that the method parameter should be bound to the value of the corresponding query
	public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
		//string.format is used to insert the value of name into the message
		return String.format("Hello %s!", name);
	}

	@GetMapping("/name")
	public String name(@RequestParam(value = "jina", defaultValue = "John")String jina){
		return String.format("Doe %s!", jina);
	}

}
