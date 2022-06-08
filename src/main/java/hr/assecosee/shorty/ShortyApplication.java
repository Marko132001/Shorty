package hr.assecosee.shorty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan({"hr.assecosee.controllers"})
@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class ShortyApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(ShortyApplication.class, args);


	}

}
