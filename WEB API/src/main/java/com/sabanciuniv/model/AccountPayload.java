package com.sabanciuniv.model;

public class AccountPayload {
	
	String id;
	String owner;
	
	
	public AccountPayload() {
		
		// TODO Auto-generated constructor stub
	}


	public AccountPayload(String id, String owner) {
		super();
		this.id = id;
		this.owner = owner;
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
	
	
	
	
	

}
