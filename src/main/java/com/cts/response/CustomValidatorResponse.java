package com.cts.response;

import java.util.ArrayList;


public class CustomValidatorResponse{
		private String result;
		private ArrayList<CustomError> errorRecords;
		public CustomValidatorResponse(String result, ArrayList<CustomError> errorRecords) {
			super();
			this.result = result;
			this.errorRecords = errorRecords;
		}
		public String getResult() {
			return result;
		}
		
		public ArrayList<CustomError> getErrorRecords() {
			return errorRecords;
		}
		
		
		
		
		

		
		
		
		
}
