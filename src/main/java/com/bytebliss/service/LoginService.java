package com.bytebliss.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.bytebliss.entity.Register;
import com.bytebliss.entity.UserInfo;
import com.bytebliss.repository.IRegisterRepository;

//@Component
@Service
public class LoginService implements UserDetailsService {
	
	@Autowired
	private IRegisterRepository registerRepository;

	  @Override
	    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	        Optional<Register> userInfo = registerRepository.findByEmail(username);
	        return userInfo.map(UserInfo::new).orElseThrow(() -> new UsernameNotFoundException("user not found: " + username));
	    }
}
