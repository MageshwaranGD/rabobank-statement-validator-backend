package com.cts.rabo;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootApplication
public class RaboApplication {
	 
	 	@Bean
	    @Primary
	    public ObjectMapper objectMapper() {
	 	
	 		ObjectMapper objectMapper = new ObjectMapper();
	        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, true);
	        return objectMapper;
	    }
	 	
	public static void main(String[] args) {
		SpringApplication.run(RaboApplication.class, args);
	}

}
