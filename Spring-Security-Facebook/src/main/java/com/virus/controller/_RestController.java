package com.virus.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.facebook.api.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.virus.service.FacebookService;

@RestController
public class _RestController {
	@Autowired
	private FacebookService facebookService;

	@GetMapping("/principal")
	public Principal userDetails(Principal principal) {
		return principal;
	}

	@GetMapping("/get")
	public User getUser() {
		return facebookService.getUser();
	}
}
