package com.example.campus_connect;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

// import com.example.campus_connect.Security.JwtProperties;

@SpringBootApplication
// @EnableConfigurationProperties(JwtProperties.class)

public class CampusConnectApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampusConnectApplication.class, args);
	}

}
