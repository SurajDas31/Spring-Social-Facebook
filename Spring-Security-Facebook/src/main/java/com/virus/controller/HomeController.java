package com.virus.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.virus.service.FacebookService;

@Controller
public class HomeController {

	@Autowired
	private FacebookService facebookService;

	@GetMapping("/")
	public String user(Principal principal, Model model) {
		return "index";
	}

	@GetMapping("/generate")
	public String generateFacebookAuthorizeUrl() {
		return facebookService.generateFacebookAuthorizeUrl();
	}

	@GetMapping("/facebook")
	public String generateAccessToken(@RequestParam String code) {
		facebookService.generateAccessToken(code);
		return "Hello";
	}

	@RequestMapping(value = "/getUser", method = RequestMethod.GET)
	public String getUser(Model model) {
		User user = facebookService.getUser();
		model.addAttribute("user", user);
		return "home";
	}

}
