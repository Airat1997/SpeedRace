package edu.wilmerbl.server;


import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) throws IOException, InterruptedException {
		SpringApplication.run(ServerApplication.class, args);
	}


}