/**
 * 
 */
package com.learn.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author sanjkul2
 *
 */

@RestController
public class AddressController {
	
	@Autowired
	AddressRepository addressRepository;
	
	@GetMapping("/advocates/{advocateId}/addresses")
	public List<Address> getAllAddresses(@PathVariable Long advocateId){
		return addressRepository.findByAdvocateId(advocateId);
	}
	
	@GetMapping("/advocates/{advocateId}/addresses/{addressId}")
	public Address getOneAddress(@PathVariable Long addressId){
		return addressRepository.findOne(addressId);
	}
}
