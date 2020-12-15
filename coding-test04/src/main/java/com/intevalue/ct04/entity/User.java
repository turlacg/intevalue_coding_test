package com.intevalue.ct04.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
	
	@Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;
    
    @Column(name = "current_balance")
    private double currentBalance;

    @Column(name = "creation_date")
    private Date creationDate;
    
    public User() {

	}

	public User(String firstName, String lastName, double currentBalance, Date creationDate) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.currentBalance = currentBalance;
		this.creationDate = creationDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public double getCurrentBalance() {
		return currentBalance;
	}

	public void setCurrentBalance(double currentBalance) {
		this.currentBalance = currentBalance;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
    
    
}
