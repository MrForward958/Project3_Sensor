package by.karzhou.project3.Project3_Rest_API;

import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class Project3_Rest_API {

	public static void main(String[] args) {
		SpringApplication.run(Project3_Rest_API.class, args);
	}

	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}

}
