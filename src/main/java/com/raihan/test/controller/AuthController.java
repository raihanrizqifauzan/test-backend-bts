package com.raihan.test.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.raihan.test.model.AuthenticationRequest;
import com.raihan.test.model.AuthenticationResponse;
import com.raihan.test.model.User;
import com.raihan.test.service.UserService;
import com.raihan.test.util.JwtUtil;

@RestController
@RequestMapping("/api/users")
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private JwtUtil jwtTokenUtil;
	
	@GetMapping("/")
	public Map getAllUser() {
		List<User> user = userService.getAllData();
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("user", user);
		return response;
	}
	
	@PostMapping("/signin")
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
			);
		} catch (BadCredentialsException e) {
			throw new Exception("User or Password wrong", e);
		}
		
		final UserDetails userDetails = userService.loadUserByUsername(authenticationRequest.getUsername());
		final String jwt = jwtTokenUtil.generateToken(userDetails);
		return ResponseEntity.ok(new AuthenticationResponse(jwt));
	}
	
	@PostMapping("/signup")
	public Map register(@RequestBody User user) {
		User newData = userService.saveUser(user);
		Map<String, Object> response = new HashMap<String, Object>();
		response.put("user", newData);
		return response;
	}
	
	
}
