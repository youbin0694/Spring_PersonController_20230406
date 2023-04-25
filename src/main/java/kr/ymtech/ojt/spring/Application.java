package kr.ymtech.ojt.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import kr.ymtech.ojt.spring.controller.PersonController;

@SpringBootApplication(scanBasePackageClasses = PersonController.class)
public class Application {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

}
