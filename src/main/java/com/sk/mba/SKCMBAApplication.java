package com.sk.mba;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class SKCMBAApplication {

	public static void main(String[] args) {
		System.out.println("aaaaaaaaaaaa-bbbbbbb-ccc");
		SpringApplication.run(SKCMBAApplication.class, args);
	}

}
