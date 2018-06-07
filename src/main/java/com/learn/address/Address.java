package com.learn.address;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.learn.advocate.Advocate;
import com.learn.user.User;

@Entity
@Table(name="address")
public class Address implements Serializable{
	
	private static final long serialVersionUID = 1L;

	public Address() {
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
    private String line1;
    private String line2;
    private String city;
    private String state;
    private String country;
    private long pincode;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "advocate")
    @JsonIgnore
    private Advocate advocate;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user")
    @JsonIgnore
    private User user;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLine1() {
		return line1;
	}
	public void setLine1(String line1) {
		this.line1 = line1;
	}
	public String getLine2() {
		return line2;
	}
	public void setLine2(String line2) {
		this.line2 = line2;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getPincode() {
		return pincode;
	}
	public void setPincode(long pincode) {
		this.pincode = pincode;
	}
	public Advocate getAdvocate() {
		return advocate;
	}
	public void setAdvocate(Advocate advocate) {
		this.advocate = advocate;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
}
