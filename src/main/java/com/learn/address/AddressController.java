/**
 * 
 */
package com.learn.address;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PutMapping("/advocates/{advocateId}/addresses/{addressId}")
	public void updateAnAdddress(@RequestBody Address address, @PathVariable Long addressId){
		Address oldAddress = addressRepository.findOne(addressId);
		if(oldAddress != null){
			address.setId(oldAddress.getId());
			addressRepository.save(address);
		}
	}
	
	@DeleteMapping("/advocates/{advocateId}/addresses/{addressId}")
	public void deleteAnAddress(@PathVariable Long addressId){
		Address address = addressRepository.findOne(addressId);
		if(address != null){
			addressRepository.delete(address);
		}
	}
	
	@GetMapping("/users/{userId}/addresses")
	public List<Address> getAllAddressesOfAUser(@PathVariable Long userId){
		return addressRepository.findByUserId(userId);
	}
	
	@GetMapping("/users/{userId}/addresses/{addressId}")
	public Address getOneAddressOfAUser(@PathVariable Long addressId){
		return addressRepository.findOne(addressId);
	}
	
	@PutMapping("/users/{userId}/addresses/{addressId}")
	public void updateAnAdddressOfAUser(@RequestBody Address address, @PathVariable Long addressId){
		Address oldAddress = addressRepository.findOne(addressId);
		if(oldAddress != null){
			address.setId(oldAddress.getId());
			addressRepository.save(address);
		}
	}
	
	@DeleteMapping("/users/{userId}/addresses/{addressId}")
	public void deleteAnAddressOfAUser(@PathVariable Long addressId){
		Address address = addressRepository.findOne(addressId);
		if(address != null){
			addressRepository.delete(address);
		}
	}
}
