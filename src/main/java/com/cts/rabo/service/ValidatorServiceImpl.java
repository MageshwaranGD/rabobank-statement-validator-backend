package com.cts.rabo.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.function.BiFunction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.cts.bean.StatementModelBean;
import com.cts.response.CustomError;



@Service
public class ValidatorServiceImpl implements ValidatorService { 
	
	private static final Logger LOGGER=LoggerFactory.getLogger(ValidatorServiceImpl.class);
	
	
	ArrayList<Integer> tranRef=new ArrayList<>();
	
	BiFunction<Float, Float, Float> add=(a,b)->a+b;
	
	@Override
	public HashMap<String, ArrayList<CustomError>> JsonValidatorSerivce(ArrayList<StatementModelBean> statements) {
	
	ArrayList<CustomError> duplicateRef=new ArrayList<>();
	ArrayList<CustomError> incorrectBal=new ArrayList<>();
	ArrayList<CustomError> dupRefNIncorrectBal=new ArrayList<>();
	
	HashMap<String, ArrayList<CustomError>> responseMap=new HashMap<>();
		
		LOGGER.info("Inside Json Validator Service");
		
		statements.stream().forEach((statement)->{

			CustomError errorRecords=new CustomError();
			errorRecords.setReference(statement.getReferenceNumber());
			errorRecords.setAccountNumber(statement.getAccountNumber());
		
			if(checkForDuplicateRef(statement)){
				if(checkForIncorrectBal(statement)){
					dupRefNIncorrectBal.add(errorRecords);
					responseMap.put("DUPLICATE_REFERENCE_INCORRECT_END_BALANCE",dupRefNIncorrectBal);
				}else{
				duplicateRef.add(errorRecords);
				responseMap.put("DUPLICATE_REFERENCE",duplicateRef);
				}
			}else if(checkForIncorrectBal(statement)){
				incorrectBal.add(errorRecords);
				responseMap.put("INCORRECT_END_BALANCE",incorrectBal);
			}
			
		});
		
		if(responseMap.isEmpty()) 
			responseMap.put("SUCCESSFUL",new ArrayList<>());
		
		
		tranRef.clear();
		return responseMap;
		 
	}
	public boolean checkForDuplicateRef(StatementModelBean statement) {
		
			if(tranRef.isEmpty() || !tranRef.contains(statement.getReferenceNumber())){
				tranRef.add(statement.getReferenceNumber());
				return false;
				}
			return true;
			
	}
	
	
	public boolean checkForIncorrectBal(StatementModelBean statement) {
		
		float endBal=statement.getEndBalance();
		float startBal=statement.getStartBalance();
		float mutation=statement.getMutation();
		
		
		BigDecimal residue=new BigDecimal(add.apply(startBal, mutation)).setScale(2, RoundingMode.HALF_UP);
		
		if(residue.floatValue()==endBal) 
			return false;
		return true;

}}