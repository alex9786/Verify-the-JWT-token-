package com.example.authentication.service;

import java.util.List;
import java.util.Optional;
import com.example.authentication.Dto.LoginDto;
import com.example.authentication.Dto.SignUpDto;
import com.example.authentication.common.UserResponse;
import com.example.authentication.entity.User;

public interface UserService {

	UserResponse signUp(SignUpDto signDto);

	UserResponse login(LoginDto logindto);

	UserResponse get(String auth) throws Exception;
	
	
	List<User> getAll();

	Optional<User> getUser(Long id);

	

	




	

}
