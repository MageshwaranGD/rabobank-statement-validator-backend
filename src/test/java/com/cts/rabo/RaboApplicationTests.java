package com.cts.rabo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.cts.rabo.controller.ValidatorAPI;
import com.cts.rabo.service.ValidatorServiceImpl;




@WebMvcTest(value = ValidatorAPI.class)
class RaboApplicationTests {
    
    @Autowired
    private MockMvc mvc;
    
    @MockBean
    private ValidatorServiceImpl service;
    
  
    
	@Test
	void json_validator_correctInput() throws Exception {
		
		String validateJson;
		String expectedJson;
		
		File resource = new ClassPathResource("records.json",RaboApplicationTests.class).getFile();
		File resource1 = new ClassPathResource("expectedResponse.json",RaboApplicationTests.class).getFile();
		

		
	
		BufferedReader bf=new BufferedReader(new FileReader(resource));
		BufferedReader bf1=new BufferedReader(new FileReader(resource1));
		Optional<String> stream=bf.lines().reduce((str1,str2)->str1+"\n"+str2);
		Optional<String> stream1=bf1.lines().reduce((str1,str2)->str1+"\n"+str2);
		validateJson=stream.get();
		expectedJson=stream1.get();
		
        RequestBuilder requestBuilder =MockMvcRequestBuilders.post("/validate")
        		.accept(MediaType.APPLICATION_JSON).content(validateJson).contentType(MediaType.APPLICATION_JSON);
        MvcResult result=mvc.perform(requestBuilder).andReturn();
        MockHttpServletResponse servletResponse =result.getResponse();
        assertEquals(200, servletResponse.getStatus());
        

		bf.close();
		bf1.close();

        
	}
	

}
