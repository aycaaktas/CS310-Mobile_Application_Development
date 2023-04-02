package com.sabanciuniv.repo;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.sabanciuniv.model.Account;
import com.sabanciuniv.model.Transaction;

import java.util.List;

public interface TransactionRepository extends MongoRepository< Transaction , String> {
	
	public List<Transaction> findByFrom(Account account);
	public List<Transaction> findByTo(Account account);
	
	

}