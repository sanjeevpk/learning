package com.learn.product;

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
import com.learn.company.Company;

@Entity
@Table(name="product")
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
    private String name;
    
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "company")
    private Company company;
    
    public Product(){
    }
    
    public Product(String name, Company company){
    	this.name = name;
    	this.company = company;
    }
    
    // name
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    // products
    public void setCompany(Company company){
    	this.company = company;
    }
    
    public Company getCompany(){
    	return this.company;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
    
    /*public String toString(){
    	String info = "";
    	
        JSONObject jsonInfo = new JSONObject();
        jsonInfo.put("name",this.name);
        
        JSONObject companyObj = new JSONObject();
        companyObj.put("name", this.company.getName());
        jsonInfo.put("company", companyObj);
        
        info = jsonInfo.toString();
        return info;
    }*/
}
