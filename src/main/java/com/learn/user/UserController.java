/**
 * 
 */
package com.learn.user;

import java.util.List;

import javax.management.RuntimeErrorException;

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

/**
 * @author sanjkul2
 *
 */

@RestController
public class UserController {
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	/**
	 * Get all Advocates as a List
	 * @return
	 */
	@RequestMapping("/users")
	public List<User> getAllUsers(){
		return (List<User>) userRepository.findAll();
	}
	
	/**
	 * Get a particular advocate by advocateId
	 * @param id
	 * @return
	 */
	@RequestMapping("/users/{id}")
	public User getAUsers(@PathVariable Long id){
		return userRepository.findOne(id);
	}
	
	/**
	 * Save an advocate along with his one or more addresses
	 * @param advocate
	 */
	@PostMapping(value="/users")
	public void saveUser(@RequestBody User user){
		/**
		 * To check whether the user email is already registered or not, if not then continue registration
		 * else throw exception and ask user to log-in
		 */
		User userExists = userRepository.findByEmail(user.getEmail());
		if(userExists != null){
			throw new RuntimeException("Email already exists!");
		}else{
			userRepository.save(user);
			if(user.getAddress() != null){
				for(Address address : user.getAddress()){
					address.setUser(user);
					addressRepository.save(user.getAddress());
				}
			}
		}
	}
	
	/**
	 * Update an advocate and his addresses
	 * @param user
	 * @param id
	 */
	@PutMapping("/users/{id}")
	public void updateUser(@RequestBody User user, @PathVariable Long id){
		User oldUser = userRepository.findOne(id);
		oldUser.setEmail(user.getEmail());
		oldUser.setMobile(user.getMobile());
		oldUser.setName(user.getName());
		oldUser.setPassword(user.getPassword());
		oldUser.setAddress(user.getAddress());
		
//		advocate.setId(oldAdvocate.getId());
		userRepository.save(oldUser);
		if(oldUser.getAddress() != null){
			for(Address address : oldUser.getAddress()){
				//address.setId(oldAdvocate.getAddress().get(0).getId());
				address.setUser(oldUser);
				addressRepository.save(address);
			}
		}
	}
	
	/**
	 * Delete an advocate
	 * @param id
	 */
	@DeleteMapping("/users/{id}")
	public void deleteAUser(@PathVariable Long id){
		User user = userRepository.findOne(id);
		if(user != null)
			userRepository.delete(user);
	}
}
