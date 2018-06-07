/**
 * 
 */
package com.learn.cases;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.learn.user.User;
import com.learn.user.UserRepository;

/**
 * @author sanjkul2
 *
 */

@RestController
public class CasesController {
	
	@Autowired
	CasesRepository casesRepository;
	
	@Autowired
	UserRepository userRepository;
	
	/**
	 * Get all users all cases
	 * @return
	 */
	@GetMapping("/users/cases")
	public List<Cases> getAllCases(){
		return (List<Cases>) casesRepository.findAll();
	}
	
	/**
	 * Get a particular user all cases
	 * @param userId
	 * @return
	 */
	@GetMapping("/users/{userId}/cases")
	public List<Cases> getAllCasesOfAUser(@PathVariable Long userId){
		return casesRepository.findByUserId(userId);
	}
	
	/**
	 * Save a case of a user
	 * @param cases
	 * @param userId
	 */
	@PostMapping("/users/{userId}/cases")
	public void saveCase(@RequestBody Cases cases, @PathVariable Long userId){
		User user = userRepository.findOne(userId);
		cases.setUser(user);
		casesRepository.save(cases);
	}
	
	/**
	 * Get a particular case of a particular user
	 * @param caseId
	 * @return
	 */
	@GetMapping("/users/{userId}/cases/{caseId}")
	public Cases getAParticularCase(@PathVariable Long caseId){
		return casesRepository.findOne(caseId);
	}
	
	@GetMapping("/users/cases/status/new")
	public List<Cases> getAllUsersNewCases(){
		return (List<Cases>) casesRepository.findByStatus("new");
	}
}
