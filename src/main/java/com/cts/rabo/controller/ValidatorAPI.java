package com.cts.rabo.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.cts.bean.StatementModelBean;
import com.cts.rabo.service.ValidatorServiceImpl;
import com.cts.response.CustomError;
import com.cts.response.CustomValidatorResponse;


@Validated
@RestController
@CrossOrigin
@RequestMapping("rabobank")
public class ValidatorAPI {

	private static final Logger LOGGER=LoggerFactory.getLogger(ValidatorAPI.class);
	
	@Autowired
	private ValidatorServiceImpl validatorService;
	
	
	@PostMapping("/statementprocessor")
	public @ResponseBody ResponseEntity<ArrayList<CustomValidatorResponse>> statementValidator(@Valid @NotEmpty @RequestBody ArrayList<StatementModelBean> statementArray){
		
		LOGGER.info("Received JSON data for Validation");
	
		ArrayList<CustomValidatorResponse> customValidatorExceptions=new ArrayList<CustomValidatorResponse>();

		
		Set<Entry<String, ArrayList<CustomError>>> responseMap=validatorService.JsonValidatorSerivce(statementArray).entrySet();
		
		responseMap.forEach((entry)->{
			CustomValidatorResponse customValidatorException=new CustomValidatorResponse(entry.getKey(),entry.getValue());
			customValidatorExceptions.add(customValidatorException);
		});

		
		return new ResponseEntity<ArrayList<CustomValidatorResponse>>(customValidatorExceptions,HttpStatus.OK);
		
	}

	@PostMapping("/validateStatement")
	public String jsonValidatorStatement(@RequestBody List<@Valid StatementModelBean> statementArray){
	
		return "Hello World";
	}
}

