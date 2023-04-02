package com.sabanciuniv.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sabanciuniv.model.Account;
import com.sabanciuniv.model.AccountPayload;
import com.sabanciuniv.model.Summary;
import com.sabanciuniv.model.Transaction;
import com.sabanciuniv.model.TransactionPayLoad;
import com.sabanciuniv.repo.AccountRepository;
import com.sabanciuniv.repo.TransactionRepository;
import com.sabanciuniv.response.Response;

import jakarta.annotation.PostConstruct;

import java.util.List;

@RestController
@RequestMapping("")

public class AycaElifAktasRestController {
	
	@Autowired private AccountRepository accountRepository;
	@Autowired private TransactionRepository transactionRepository;
	
	
	
	
	@PostConstruct
	public void init() 
	{
		
		if(accountRepository.count() ==0) 
		{
	        


			
			Account acc1 = new Account("1111","Jack Johns");
			
			Account acc2 = new Account("2222","Henry Willams");
			
			accountRepository.save(acc1);
			accountRepository.save(acc2);
			
	       

			Double at1=1500.0;

			Double at2=2500.0;
			
			Transaction trs1 = new Transaction(at1,acc1,acc2);
			
			Transaction trs2 = new Transaction(at2,acc2,acc1);
			
			transactionRepository.save(trs1);
			transactionRepository.save(trs2);

			

			
		}
	
	
	

    }
	
	
	@PostMapping("/account/save")
	public Response<Account> saveAccount (@RequestBody AccountPayload payload ) {
		
		Response<Account> response = new Response<Account>();
		
		if(payload.getOwner() == null || payload.getId() == null) {
			
			response.setMessage("ERROR:missing fields");
			response.setData(null);

		}
		else {
			response.setMessage("SUCCESS");
			Account act3 = new Account();
			act3.setId(payload.getId());
			act3.setOwner(payload.getOwner());
			response.setData(act3);
			Account actsave = accountRepository.save(act3);

			
		}
		
		
			return response;
		
		
	}
	
	
	
	
	
	@PostMapping("/transaction/save")
	public Response<Transaction> saveTransaction(@RequestBody TransactionPayLoad payload )
    {
		
		Response<Transaction> response = new Response<Transaction>();
        if(payload.getAmount() == null || payload.getToAccountId() == null || payload.getFromAccountId()== null) {
			
			response.setMessage("ERROR:missing fields");
			response.setData(null);

		}
        else if(!( accountRepository.existsById(payload.getFromAccountId()) && accountRepository.existsById(payload.getToAccountId()) )  ) 
        {
        	response.setMessage("ERROR: account id");
			response.setData(null);
        }
		else 
		{
			response.setMessage("SUCCESS");
			
			Transaction trs3 = new Transaction();
			
			trs3.setAmountTransferred(payload.getAmount());
			
			
			
			Account actf = new Account();
			actf= accountRepository.findById(payload.getFromAccountId()).get();
			
			trs3.setFrom(actf);
			
			Account actt = new Account();
			actt= accountRepository.findById(payload.getToAccountId()).get();
			trs3.setTo(actt);
			
			
			response.setData(trs3);
			Transaction trssave = transactionRepository.save(trs3);

			
		}
		
		
		return response;
		
		
	}
	
	
	@GetMapping("/account/{accountId}")
	public Response<Summary>allTransactions(@PathVariable String accountId){
		
		Response<Summary> response = new Response<Summary>();
		
		if(!accountRepository.existsById(accountId)) {
			
			response.setMessage("ERROR:account doesnt exist!");
			response.setData(null);
			
		}
		else {
			
			response.setMessage("SUCCESS");
		
			
			Summary summary = new Summary();
			Account acttt = new Account();
			acttt=accountRepository.findById(accountId).get();
			summary.setCreateDate(acttt.getCreateDate());
			summary.setId(accountId);
			summary.setOwner(acttt.getOwner());
			
			
			
			Double bal=0.0;
			List<Transaction>list2 = transactionRepository.findByFrom(acttt);
			List<Transaction>list3 = transactionRepository.findByTo(acttt);
			
			for(Transaction temp : list2) {
				bal=bal-temp.getAmountTransferred();
			}
			for(Transaction tempp : list3) {
				bal=bal+tempp.getAmountTransferred();
			}
			
			
			summary.setTransactionOut(list2);
			summary.setTransactionIn(list3);
			summary.setBalance(bal);
			
			
			response.setData(summary);
					
		}
		
			
		
		return response;
		
	}
		
	@GetMapping("/transaction/to/{accountId}")	
	public Response<List<Transaction>> intransactions(@PathVariable String accountId){
		
		
		
		Response<List<Transaction>> response = new Response();
		
           if(!accountRepository.existsById(accountId)) 
           {
			
			response.setMessage("ERROR:account doesnt exist!");
			response.setData(null);
			
		   }
           else 
           {
        	      
        	   
        	   response.setMessage("SUCCESS");
        	   Account acc = new Account();
   			   acc=accountRepository.findById(accountId).get();
   			   List<Transaction>listin = transactionRepository.findByTo(acc);
   			  response.setData(listin);
        	      
        	   
           }
		  
	
		return response;
		
	}
		
	
	@GetMapping("/transaction/from/{accountId}")	
	public Response<List<Transaction>> outtransactions(@PathVariable String accountId){
		
		
		
		Response<List<Transaction>> response = new Response();
		
           if(!accountRepository.existsById(accountId)) 
           {
			
			response.setMessage("ERROR:account doesnt exist!");
			response.setData(null);
			
		   }
           else 
           {
        	      
        	   
        	   response.setMessage("SUCCESS");
        	   Account accc = new Account();
   			   accc=accountRepository.findById(accountId).get();
   			   List<Transaction>listout = transactionRepository.findByFrom(accc);
   			  response.setData(listout);
        	      
        	   
           }
		  
	
		return response;
		
	}

	
	
	
	
	
	
	
	
	
	
	
	
	
}

