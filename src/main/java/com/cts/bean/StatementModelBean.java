package com.cts.bean;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import com.fasterxml.jackson.annotation.JsonProperty;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "All details about the Statement format")

public class StatementModelBean {

	@JsonProperty("Reference")
	@Min(value = 1)
	private int referenceNumber;
	@ApiModelProperty(notes="Account Number Cannot be Empty or Null")
	@JsonProperty("AccountNumber")
	@NotNull
	private String accountNumber;
	@JsonProperty("Description")
	private String description;
	@JsonProperty("Start Balance")
	private float startBalance;
	@JsonProperty("Mutation")
	private float mutation;
	@JsonProperty("End Balance")
	private float endBalance;
	
	
	public StatementModelBean() {
		super();
		// TODO Auto-generated constructor stub
	}
	public StatementModelBean(int referenceNumber, @NotNull String accountNumber, String description,
			float startBalance, float mutation, float endBalance) {
		super();
		this.referenceNumber = referenceNumber;
		this.accountNumber = accountNumber;
		this.description = description;
		this.startBalance = startBalance;
		this.mutation = mutation;
		this.endBalance = endBalance;
	}
	public int getReferenceNumber() {
		return referenceNumber;
	}
	public void setReferenceNumber(int referenceNumber) {
		this.referenceNumber = referenceNumber;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public float getStartBalance() {
		return startBalance;
	}
	public void setStartBalance(float startBalance) {
		this.startBalance = startBalance;
	}
	public float getMutation() {
		return mutation;
	}
	public void setMutation(float mutation) {
		this.mutation = mutation;
	}
	public float getEndBalance() {
		return endBalance;
	}
	public void setEndBalance(float endBalance) {
		this.endBalance = endBalance;
	}
	
	@Override
	public String toString() {
		return "StatementModelBean [referenceNumber=" + referenceNumber + ", accountNumber=" + accountNumber
				+ ", description=" + description + ", startBalance=" + startBalance + ", mutation=" + mutation
				+ ", endBalance=" + endBalance + "]";
	}
	
	
	
	
}
