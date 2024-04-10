package com.appsdeveloperblog.app.ws.security;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.appsdeveloperblog.app.ws.service.UserService;

import org.springframework.security.config.annotation.web.builders.*;
import org.springframework.http.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter{

	private final UserService userService;
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	public WebSecurity(UserService userService, BCryptPasswordEncoder bCryptPasswordEncoder )
	{
		
		this.bCryptPasswordEncoder =bCryptPasswordEncoder;
		this.userService=userService;
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		http.csrf().disable().authorizeRequests().antMatchers(HttpMethod.POST,"/users") 
		.permitAll().anyRequest().authenticated();
	}
	@Override
	public void configure(AuthenticationManagerBuilder auth) throws Exception{
		
		auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
	}
	
}
