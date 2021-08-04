package com.virus.service;

import org.springframework.social.facebook.api.User;

public interface FacebookService {
	public String generateFacebookAuthorizeUrl();

	public void generateAccessToken(String code);

	public User getUser();
}
