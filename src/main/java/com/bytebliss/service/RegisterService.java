package com.bytebliss.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.bytebliss.entity.Register;
import com.bytebliss.repository.IRegisterRepository;

@Service
public class RegisterService {
	
	@Autowired
	public  IRegisterRepository registerRepository;
	
	@Autowired
	public PasswordEncoder passwordEncoder;
	
	public Register addRegsiter(Register register) {
		if(registerRepository.existsByEmail(register.getEmail())) {
			return null;
		}
		register.setPassword(passwordEncoder.encode(register.getPassword()));
		register.setConfirmPassword(passwordEncoder.encode(register.getConfirmPassword()));
		return registerRepository.save(register);
		
	}
}
