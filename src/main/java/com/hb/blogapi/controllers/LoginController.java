package com.hb.blogapi.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.hb.blogapi.dto.UserDTO;
import com.hb.blogapi.security.JwtTokenUtil;

@RestController
@RequestMapping("/api/public")
@CrossOrigin(origins = "*")
public class LoginController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;

	private Logger logger = LoggerFactory.getLogger(LoginController.class);

	@PostMapping("login")
	public ResponseEntity<String> login(@RequestBody UserDTO user) {

		try {
			// Calling the authenticate method of the AuthenticationManager,
			// Spring Security will get the user from DB and compare password.
			Authentication authentication = authenticationManager
					.authenticate(new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword()));

			User authenticatedUser = (User) authentication.getPrincipal();

			String token = jwtTokenUtil.generateAccessToken(authenticatedUser);

			logger.info("Generated token for user " + user.getUsername() + " is " + token);

			// Token should be set on the response header 'AUTHORIZATION'
			return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, token).body(user.getUsername() + " logged");

		} catch (BadCredentialsException e) {
			logger.error("Username or password are not valid");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}

	}

}
