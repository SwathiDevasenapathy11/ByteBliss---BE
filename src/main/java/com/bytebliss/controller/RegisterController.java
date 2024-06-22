package com.bytebliss.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bytebliss.DTO.LoginRequest;
import com.bytebliss.entity.Register;
import com.bytebliss.service.JwtService;
import com.bytebliss.service.RegisterService;

@RequestMapping("/register")
@RestController
@CrossOrigin
public class RegisterController {

	@Autowired
	public RegisterService registerService;
	
	@Autowired
	public AuthenticationManager authenticationManager;
	
	@Autowired
	public JwtService jwtService;

	
	@PostMapping("/add")
	public ResponseEntity<?> add(@RequestBody Register register) {
		if(register.getRole()==null) {
			register.setRole("USER");
		}
		Register savedRegister = registerService.addRegsiter(register);
		if(savedRegister == null) {
			return new ResponseEntity<>("Emai Already Exists" , HttpStatus.CONFLICT);
		}
		return new ResponseEntity<>("Saved" , HttpStatus.CREATED);
	}
	
	
//	@PostMapping("/login")
//	public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
//	    System.out.println("Login request received for email: " + loginRequest.getUsername());
//	    try {
//	        Authentication authentication = authenticationManager.authenticate(
//	            new UsernamePasswordAuthenticationToken(
//	                loginRequest.getUsername(),
//	                loginRequest.getPassword()
//	            )
//	        );
//	        System.out.println("Authentication successful for email: " + loginRequest.getUsername());
//	        return ResponseEntity.ok("Login successful");
//	    } catch (Exception e) {
//	        System.out.println("Authentication failed for email: " + loginRequest.getUsername());
//	        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid username or password");
//	    }
//	}
	
	@PostMapping("/login")
	public String authenticateGetToken(@RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		if(authentication.isAuthenticated()) {
			return jwtService.generateToken(loginRequest.getUsername());
		}else {
			throw new UsernameNotFoundException("User note found");
		}
	}
	
	@GetMapping("/getAll")
	public String all () {
		return "Get ALL THING GOAT DE NEE";
	}

}

