package com.sabanciuniv.response;


public class Response<T> {
	
	private String message;
	private T data;
	
	
	
	
	public Response() {
		
		// TODO Auto-generated constructor stub
	}




	public Response( T data, String message) {
		super();
		this.data = data;
		this.message = message;
	}




	public T getData() {
		return data;
	}




	public void setData(T data) {
		this.data = data;
	}




	public String getMessage() {
		return message;
	}




	public void setMessage(String message) {
		this.message = message;
	}
	
	
	

}
