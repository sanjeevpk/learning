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
	
	@GetMapping("/cases")
	public List<Cases> getAllCases(){
		return (List<Cases>) casesRepository.findAll();
	}
	
	@PostMapping("/cases/{userId}")
	public void saveCase(@RequestBody Cases cases, @PathVariable Long userId){
		User user = userRepository.findOne(userId);
		cases.setUser(user);
		casesRepository.save(cases);
	}
}
