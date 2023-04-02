package com.sabanciuniv.model;

import java.time.LocalDateTime;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;

import org.springframework.data.mongodb.core.mapping.Document;

@Document



public class Transaction {
	
	
	@Id private String id;
	@DBRef
	private Account from;
	@DBRef
	private Account to;
	private LocalDateTime createDate;
	private Double amountTransferred;
	

	public Transaction() {
		LocalDateTime now = LocalDateTime.now();
		 this.setCreateDate(now);
		// TODO Auto-generated constructor stub
	}


	public Transaction(String id, LocalDateTime createDate, Double amountTransferred, Account from, Account to) {
		super();
		this.id = id;
		this.createDate = createDate;
		this.amountTransferred = amountTransferred;
		this.from = from;
		this.to = to;
	}


	public Transaction(LocalDateTime createDate, Double amountTransferred, Account from, Account to) {
		super();
		this.createDate = createDate;
		this.amountTransferred = amountTransferred;
		this.from = from;
		this.to = to;
		
	}
	


	

	public Transaction(Double amountTransferred, Account from, Account to) {
		super();
		this.amountTransferred = amountTransferred;
		this.from = from;
		this.to = to;
	}


	public String getId() {
		return id;
	}


	public void setId(String id) {
		this.id = id;
	}


	public LocalDateTime getCreateDate() {
		return createDate;
	}


	public void setCreateDate(LocalDateTime createDate) {
		this.createDate = createDate;
	}


	public Double getAmountTransferred() {
		return amountTransferred;
	}


	public void setAmountTransferred(Double amountTransferred) {
		this.amountTransferred = amountTransferred;
	}


	public Account getFrom() {
		return from;
	}


	public void setFrom(Account from) {
		this.from = from;
	}


	public Account getTo() {
		return to;
	}


	public void setTo(Account to) {
		this.to = to;
	}

	
	

}
