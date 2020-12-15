package com.intevalue.ct04.controller;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.intevalue.ct04.entity.TransactionLog;
import com.intevalue.ct04.entity.User;
import com.intevalue.ct04.exception.ResourceNotFoundException;
import com.intevalue.ct04.repository.TransactionLogRepository;
import com.intevalue.ct04.repository.UsersRepository;

@RestController
@RequestMapping("/api/user")
public class UsersController {
	
	@Autowired
    private UsersRepository usersRepository;
	
	@Autowired
	private TransactionLogRepository tlRepository;
	
    // create user
    @GetMapping("/addUser/{firstName}/{lastName}")
    public User createUser(@PathVariable("firstName") String firstName,
    		@PathVariable("lastName") String lastName) {
    	
        return this.usersRepository.save(
        		new User(firstName, lastName, 0.0, new Date()));
    }
    
    //get user
    @GetMapping("/getUser/{id}")
    public User getUserById(@PathVariable("id") long userId) {
        return this.usersRepository.findById(userId)
            .orElseThrow(()-> new ResourceNotFoundException("User not found with id :" + userId));
    }
    
    
  //get users transactions
    @GetMapping("/getUserTransactions/{id}")
    public List<TransactionLog> getUserTransactions(@PathVariable("id") long userId) {
    	 return this.tlRepository.findByUserIdOrderByTransactionDateDesc(userId)
    	            .orElseThrow(()-> new ResourceNotFoundException("Logs not found with id :" + userId));
    }
    
    //deposit
    @GetMapping("/deposit/{id}/{amount}")
    public Map deposit(@PathVariable("id") long userId,
    		@PathVariable("amount") double amount) {

		User existingUser = this.usersRepository.findById(userId)
	            .orElseThrow(()-> new ResourceNotFoundException("User not found with id :" + userId));
		
		//add deposit amount to current balance
	   existingUser.setCurrentBalance(Double.sum(existingUser.getCurrentBalance(), amount));
	    
	   if(this.usersRepository.save(existingUser) != null) {
		   //log transaction
		   this.tlRepository.save(new TransactionLog(userId, "deposit", amount, new Date()));
		   
		   return Collections.singletonMap("response", "Deposit transaction successful.");
	   }
	   
	   return Collections.singletonMap("response", "Deposit transaction failed.");
    }
    
    
    //withdraw
    @GetMapping("/withdraw/{id}/{amount}")
    public Map withdraw(@PathVariable("id") long userId,
    		@PathVariable("amount") double amount) {

		User existingUser = this.usersRepository.findById(userId)
	            .orElseThrow(()-> new ResourceNotFoundException("User not found with id :" + userId));
		
		//subtract withdrawal amount to current balance
	   existingUser.setCurrentBalance(existingUser.getCurrentBalance() - amount);
	    
	   if(this.usersRepository.save(existingUser) != null) {
		   //log transaction
		   this.tlRepository.save(new TransactionLog(userId, "withdraw", amount, new Date()));
		   
		   return Collections.singletonMap("response", "Withdrawal transaction successful.");
	   }
	   
	   return Collections.singletonMap("response", "Withdrawal transaction failed.");
    }
    
    
    //transfer
    @GetMapping("/transfer/{fromId}/{toId}/{amount}")
    public Map withdraw(@PathVariable("fromId") long fromId,
    		@PathVariable("toId") long toId,
    		@PathVariable("amount") double amount) {

		User fromUser = this.usersRepository.findById(fromId)
	            .orElseThrow(()-> new ResourceNotFoundException("Source user not found with id :" + fromId));
		
		User toUser = this.usersRepository.findById(toId)
	            .orElseThrow(()-> new ResourceNotFoundException("Recepient user not found with id :" + toId));
		
		//subtract withdrawal amount to current balance
		fromUser.setCurrentBalance(fromUser.getCurrentBalance() - amount);
		
		//add deposit amount to current balance
		toUser.setCurrentBalance(Double.sum(toUser.getCurrentBalance(), amount));
	    
	   if(this.usersRepository.save(fromUser) != null && this.usersRepository.save(toUser)!=null) {
		   //log transaction
		   this.tlRepository.save(new TransactionLog(fromId, "transfer(sent)", amount, new Date()));
		   this.tlRepository.save(new TransactionLog(toId, "transfer(receive)", amount, new Date()));
		   
		   return Collections.singletonMap("response", "Transfer transaction successful.");
	   }
	   
	   return Collections.singletonMap("response", "Withdrawal transaction failed.");
    }
}
