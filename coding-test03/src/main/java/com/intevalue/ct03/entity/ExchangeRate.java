package com.intevalue.ct03.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "exchange_rate")
public class ExchangeRate {

	 @Id
     private long id;
	 
	 @Column(name = "from_currency")
	 private String fromCurrency;
	 
	 @Column(name = "to_currency")
	 private String toCurrency;
	 
	 @Column(name = "rate")
	 private double rate;
	 
	 public ExchangeRate() {

	 }
	 

	public ExchangeRate(String fromCurrency, String toCurrency, double rate) {
		super();
		this.fromCurrency = fromCurrency;
		this.toCurrency = toCurrency;
		this.rate = rate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFromCurrency() {
		return fromCurrency;
	}

	public void setFromCurrency(String fromCurrency) {
		this.fromCurrency = fromCurrency;
	}

	public String getToCurrency() {
		return toCurrency;
	}

	public void setToCurrency(String toCurrency) {
		this.toCurrency = toCurrency;
	}

	public double getRate() {
		return rate;
	}

	public void setRate(double rate) {
		this.rate = rate;
	}
	 
	
}
