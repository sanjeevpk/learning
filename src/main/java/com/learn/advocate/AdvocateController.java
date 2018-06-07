/**
 * 
 */
package com.learn.advocate;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.learn.address.Address;
import com.learn.address.AddressRepository;
import com.learn.user.UserRepository;

/**
 * @author sanjkul2
 *
 */

@RestController
public class AdvocateController {
	
	@Autowired
	AdvocateRepository advocateRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	/**
	 * Get all Advocates as a List
	 * @return
	 */
	@RequestMapping("/advocates")
	public List<Advocate> getAllAdvocates(){
		return (List<Advocate>) advocateRepository.findAll();
	}
	
	/**
	 * Get a particular advocate by advocateId
	 * @param id
	 * @return
	 */
	@RequestMapping("/advocates/{id}")
	public Advocate getAnAdvocate(@PathVariable Long id){
		return advocateRepository.findOne(id);
	}
	
	/**
	 * Save an advocate along with his one or more addresses
	 * @param advocate
	 */
	@PostMapping(value="/advocates")
	public void saveAdvocate(@RequestBody Advocate advocate){
		advocateRepository.save(advocate);
		if(advocate.getAddress() != null){
			for(Address address : advocate.getAddress()){
				address.setAdvocate(advocate);
				addressRepository.save(advocate.getAddress());
			}
		}
	}
	
	/**
	 * Update an advocate and his addresses
	 * @param advocate
	 * @param id
	 */
	@PutMapping("/advocates/{id}")
	public void updateAdvocate(@RequestBody Advocate advocate, @PathVariable Long id){
		Advocate oldAdvocate = advocateRepository.findOne(id);
		oldAdvocate.setEmail(advocate.getEmail());
		oldAdvocate.setMobile(advocate.getMobile());
		oldAdvocate.setName(advocate.getName());
		oldAdvocate.setPassword(advocate.getPassword());
		oldAdvocate.setAddress(advocate.getAddress());
		
//		advocate.setId(oldAdvocate.getId());
		advocateRepository.save(oldAdvocate);
		if(oldAdvocate.getAddress() != null){
			for(Address address : oldAdvocate.getAddress()){
				//address.setId(oldAdvocate.getAddress().get(0).getId());
				address.setAdvocate(oldAdvocate);
				addressRepository.save(address);
			}
		}
	}
	
	/**
	 * Delete an advocate
	 * @param id
	 */
	@DeleteMapping("/advocates/{id}")
	public void deleteAnAdvocate(@PathVariable Long id){
		Advocate advocate = advocateRepository.findOne(id);
		if(advocate != null)
			advocateRepository.delete(advocate);
	}
}
