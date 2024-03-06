package com.backend.huffPage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class HuffPageApplication {

	public static void main(String[] args) throws ClassNotFoundException {
	  HuffPageConnection connection = new HuffPageConnection();
	  connection.connect();
		SpringApplication.run(HuffPageApplication.class, args);
	}
	

}
