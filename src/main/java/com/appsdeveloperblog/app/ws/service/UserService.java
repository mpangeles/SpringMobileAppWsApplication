package com.appsdeveloperblog.app.ws.service;

import org.springframework.security.core.userdetails.*;

import com.appsdeveloperblog.app.ws.shared.dto.UserDto;


public interface UserService extends UserDetailsService {
 UserDto createUser(UserDto user);
}
