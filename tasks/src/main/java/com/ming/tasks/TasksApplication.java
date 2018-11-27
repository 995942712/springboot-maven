package com.ming.tasks;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync//开启异步调用
@SpringBootApplication
public class TasksApplication {

	public static void main(String[] args) {
		SpringApplication.run(TasksApplication.class, args);
	}

}