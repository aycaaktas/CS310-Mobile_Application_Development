package com.sabanciuniv.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Summary {
	
	private String id; 
	private String owner;
	private LocalDateTime createDate;
	private List<Transaction> transactionOut;
	private List<Transaction> transactionIn;
	
	Double balance;
	
	
	
	
	
	
	
	public Summary() {
		
		
		// TODO Auto-generated constructor stub
		
	    balance=0.0;
	}
	











	public Summary(String id, String owner, LocalDateTime createDate, List<Transaction> transactionOut,
			List<Transaction> transactionIn, Double balance) {
		super();
		this.id = id;
		this.owner = owner;
		this.createDate = createDate;
		this.transactionOut = transactionOut;
		this.transactionIn = transactionIn;
		this.balance = balance;
	}












	public String getId() {
		return id;
	}












	public void setId(String id) {
		this.id = id;
	}












	public String getOwner() {
		return owner;
	}












	public void setOwner(String owner) {
		this.owner = owner;
	}












	public LocalDateTime getCreateDate() {
		return createDate;
	}












	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}












	public List<Transaction> getTransactionOut() {
		return transactionOut;
	}







	public void setTransactionOut(List<Transaction> transactionOut) {
		this.transactionOut = transactionOut;
	}







	public List<Transaction> getTransactionIn() {
		return transactionIn;
	}







	public void setTransactionIn(List<Transaction> transactionIn) {
		this.transactionIn = transactionIn;
	}







	public Double getBalance() {
		return balance;
	}







	public void setBalance(Double balance) {
		this.balance = balance;
	}
	
	
	
	
	
	
	
	
	


}

