package com.stone;

import java.util.Date;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class StudentServiceApplication {
	
	@RequestMapping(value = "echo/{name}")
	public String echoStudentName(@PathVariable(name="name") String name) {
		return "hello " + name + " Responsed on : " + new Date();
	}
	
	@RequestMapping(value="/getStudent/{name}")
	public Student getStuentDetails(@PathVariable(name="name") String name) {
		return new Student(name, "beijing");
	}
	

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
	}
}


class Student {
	String name;
	String address;

	public Student(String name, String address) {
		super();
		this.name = name;
		this.address = address;
	}
	
	public String getName() {
		return name;
	}
	
	public String getAddress() {
		return address;
	}
}