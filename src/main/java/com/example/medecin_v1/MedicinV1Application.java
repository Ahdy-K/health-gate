package com.example.medecin_v1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
public class MedicinV1Application {

	public static void main(String[] args) {
		SpringApplication.run(MedicinV1Application.class, args);
	}

}
