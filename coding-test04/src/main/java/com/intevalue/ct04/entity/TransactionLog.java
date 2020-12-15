package com.intevalue.ct04.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "transaction_log")
public class TransactionLog {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "user_id")
	private long userId;
	
	@Column(name = "transaction_type")
	private String transactionType;
	
	@Column(name = "transaction_amt")
	private double transactionAmt;
	
	@Column(name = "transaction_date")
	private Date transactionDate;
	
	public TransactionLog() {

	}

	public TransactionLog(long userId, String transactionType, double transactionAmt, Date transactionDate) {
		super();
		this.userId = userId;
		this.transactionType = transactionType;
		this.transactionAmt = transactionAmt;
		this.transactionDate = transactionDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public double getTransactionAmt() {
		return transactionAmt;
	}

	public void setTransactionAmt(double transactionAmt) {
		this.transactionAmt = transactionAmt;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}
	
	

}
