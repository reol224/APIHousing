package com.conestoga.APIHousing;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
public class ApiHousingApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiHousingApplication.class, args);
	}

	@RestController
	public static class HomeController {

		@GetMapping("/")
		public String home() {
			return "Welcome to the API Housing application!";
		}
	}

}