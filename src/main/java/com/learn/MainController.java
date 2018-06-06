package com.learn;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {
	
	@RequestMapping("/")
	public String homePage(Model model){
		model.addAttribute("message", "Hello Springboot");
		return "index";
	}
	
	@RequestMapping("/registration/advocate")
	public String registerAdvocate(Model model){
		model.addAttribute("advocate", "Advocate Registartion");
		return "registerAdvocate";
	}
}
