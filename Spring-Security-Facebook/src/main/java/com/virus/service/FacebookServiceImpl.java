package com.virus.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.social.connect.Connection;
import org.springframework.social.facebook.api.Facebook;
import org.springframework.social.facebook.api.User;
import org.springframework.social.facebook.connect.FacebookConnectionFactory;
import org.springframework.social.oauth2.AccessGrant;
import org.springframework.social.oauth2.OAuth2Parameters;
import org.springframework.stereotype.Service;

@Service
public class FacebookServiceImpl implements FacebookService {

	private static Logger log = LoggerFactory.getLogger(FacebookServiceImpl.class);

	private AccessGrant accessToken;

	FacebookConnectionFactory connectionFactory = new FacebookConnectionFactory("912329979618346",
			"d4e59aa4db453f101541e3e4733bc813");

	@Override
	public String generateFacebookAuthorizeUrl() {
		OAuth2Parameters params = new OAuth2Parameters();
		params.setRedirectUri("http://localhost:8080/facebook");
		params.setScope("email,public_profile");
		String url = connectionFactory.getOAuthOperations().buildAuthenticateUrl(params);
		log.info(url);
		return "redirect:" + url;
	}

	@Override
	public void generateAccessToken(String code) {
		accessToken = connectionFactory.getOAuthOperations().exchangeForAccess(code, "http://localhost:8080/facebook",
				null);

	}

	@Override
	public User getUser() {

		Connection<Facebook> connection = connectionFactory.createConnection(accessToken);
		Facebook facebook = connection.getApi();

		String[] fields = { "id", "email", "first_name", "last_name", "gender", "birthday", "location", "name" };
		User user = facebook.fetchObject("me", User.class, fields);

		return user;
	}
}
