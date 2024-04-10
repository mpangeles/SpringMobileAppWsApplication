package com.appsdeveloperblog.app.ws.service.Impl;

import java.util.ArrayList;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.appsdeveloperblog.app.ws.UserRepository;
import com.appsdeveloperblog.app.ws.io.entity.UserEntity;
import com.appsdeveloperblog.app.ws.service.UserService;
import com.appsdeveloperblog.app.ws.shared.Utils;
import com.appsdeveloperblog.app.ws.shared.dto.UserDto;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	
	UserRepository userRepository;
	@Autowired
	Utils utils;
	
	@Autowired
	BCryptPasswordEncoder bCryptPasswordEncode;
	@Override
	public UserDto createUser(UserDto user) {
		
	
		if(userRepository.findByEmail(user.getEmail())!=null) throw new RuntimeException("Record already exists");
		
		UserEntity userEntity= new UserEntity();
		BeanUtils.copyProperties(user, userEntity); 
		
		//Pase value
		String publicUserId=utils.generateUserId(30);
		userEntity.setEncryptedpassword(bCryptPasswordEncode.encode(user.getPassword()));
		userEntity.setUserId(publicUserId);
		
		
		
		UserEntity storedUserDetails= userRepository.save(userEntity);
		UserDto returnValue= new UserDto();
		BeanUtils.copyProperties(storedUserDetails, returnValue);
		return returnValue;
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		UserEntity userEntity= userRepository.findByEmail(username);
		if(userEntity==null) throw new UsernameNotFoundException(username);
		
		return new User(username, userEntity.getEncryptedpassword(),new ArrayList<>());
	}

}
