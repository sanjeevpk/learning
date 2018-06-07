/**
 * 
 */
package com.learn.user;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.learn.address.Address;
import com.learn.cases.Cases;

/**
 * @author sanjkul2
 *
 */

@Entity
@Table(name="user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	public User() {
	}
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private long id;
	
    private String name;
    private String email;
    private String mobile;
    private String password;
    
    @OneToMany(mappedBy ="user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Address> address;
    
    @OneToMany(mappedBy ="user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    private List<Cases> cases;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public List<Address> getAddress() {
		return address;
	}

	public void setAddress(List<Address> address) {
		this.address = address;
	}
}
