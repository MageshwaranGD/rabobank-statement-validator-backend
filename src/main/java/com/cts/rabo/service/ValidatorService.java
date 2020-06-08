package com.cts.rabo.service;

import java.util.ArrayList;
import java.util.HashMap;

import com.cts.bean.StatementModelBean;
import com.cts.response.CustomError;


@FunctionalInterface
public interface ValidatorService {

	public HashMap<String, ArrayList<CustomError>> JsonValidatorSerivce(ArrayList<StatementModelBean> statements);
}
