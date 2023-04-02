package com.sabanciuniv.model;

public class TransactionPayLoad {
	
	
	
	private String fromAccountId;
	private String toAccountId;
	private Double amount;
	
	
	
	

	public TransactionPayLoad() {
		
		// TODO Auto-generated constructor stub
	}





	public TransactionPayLoad(String fromAccountId, String toAccountId, Double amount) {
		super();
		this.fromAccountId = fromAccountId;
		this.toAccountId = toAccountId;
		this.amount = amount;
	}





	public String getFromAccountId() {
		return fromAccountId;
	}





	public void setFromAccountId(String fromAccountId) {
		this.fromAccountId = fromAccountId;
	}





	public String getToAccountId() {
		return toAccountId;
	}





	public void setToAccountId(String toAccountId) {
		this.toAccountId = toAccountId;
	}





	public Double getAmount() {
		return amount;
	}





	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	

}
