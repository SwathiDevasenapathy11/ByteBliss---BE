package com.bytebliss.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bytebliss.filter.JwtAuthFilter;
import com.bytebliss.service.LoginService;

@Configuration
@EnableWebSecurity
//@EnableMethodSecurity
public class SecurityConfig {
	
	   @Bean
	   public UserDetailsService userDetailService() {
		   return new LoginService();
	   }
	   
	
	   @Autowired
	   private JwtAuthFilter jwtAuthFilter;
	
//	   @Bean
//	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//	        http
//	            .csrf(AbstractHttpConfigurer::disable) 
//	            .authorizeHttpRequests(authz -> authz
//	                .requestMatchers("/register/add" , "/register/login").permitAll() 
//	                .anyRequest().authenticated() 
//	            )
//	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
//	            .httpBasic(httpBasic -> {})
//	            .authenticationProvider(authenticationProvider())
//	            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
//	            .build();
//
//	        return http.build();
//	    }
	  
	   @Bean
	    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
	        http
	            .csrf(AbstractHttpConfigurer::disable) 
	            .authorizeHttpRequests(authz -> authz
	                .requestMatchers("/register/add", "/register/login").permitAll() 
	                .anyRequest().authenticated() 
	            )
	            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) 
	            .httpBasic(httpBasic -> {})
	            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);
	        
	        http.authenticationProvider(authenticationProvider());
	        
	        return http.build();
	    }
	   
	   @Bean
	    public PasswordEncoder passwordEncoder() {
	        return new BCryptPasswordEncoder();
	    }
	   
	   @Bean
	   public AuthenticationProvider authenticationProvider() {
		   System.out.println("Creating AuthenticationProvider");
		   DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		   authenticationProvider.setUserDetailsService(userDetailService());
		   authenticationProvider.setPasswordEncoder(passwordEncoder());
		   return authenticationProvider; 
	   }
	   
		@Bean
		public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
			return config.getAuthenticationManager();
		}
}
