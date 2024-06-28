package com.example.authentication.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.example.authentication.Dto.LoginDto;
import com.example.authentication.Dto.RequestDto;
import com.example.authentication.Dto.SignUpDto;
import com.example.authentication.common.JwtToken;
import com.example.authentication.common.UserResponse;
import com.example.authentication.entity.User;
import com.example.authentication.repository.UserRepository;

import io.jsonwebtoken.Jwts;

@Service
public class UserServiceImp implements UserService {

	@Autowired
	UserRepository userRepo;

	@Autowired
	JwtToken jwtToken;
	
	@Autowired
	RequestDto requestDto;
	

	@Override
	public UserResponse signUp(SignUpDto signDto) {
		UserResponse userResponse = new UserResponse();
		User user = new User();
		user.setName(signDto.getName());
		user.setEmailId(signDto.getEmailId());
		user.setPassword(signDto.getPassword());
		user.setGender(signDto.getGender());
		user.setContact(signDto.getContact());
		userRepo.save(user);
		userResponse.setStatus(HttpStatus.OK.value());
		userResponse.setData("successfully");
		return userResponse;
	}

	@Override
	public UserResponse login(LoginDto loginDto) {
		UserResponse userResponse = new UserResponse();

		User user = userRepo.findOneByNameAndPassword(loginDto.getName(), loginDto.getPassword());

		if (user == null) {
			userResponse.setData(" login failed");
			userResponse.setStatus(HttpStatus.BAD_REQUEST.value());
			userResponse.setError("bad request");
			return userResponse;
		} else {

			userResponse.setStatus(HttpStatus.OK.value());
			String token = jwtToken.generateJwt(user);
			Map<String, Object> data = new HashMap<String, Object>();
			data.put("token created", token);
			userResponse.setData(data);
			return userResponse;
		}

	}

	@Override
	public UserResponse get(String auth) throws Exception {
		UserResponse userResponse = new UserResponse();
		try {
			jwtToken.verify(auth);
			userResponse.setStatus(HttpStatus.OK.value());
			userResponse.setData("this is valid token");
			return userResponse;
		} catch (Exception e) {
			userResponse.setStatus(HttpStatus.UNAUTHORIZED.value());
			userResponse.setError("This is unvalid token");
			userResponse.setData("unAuthorized");
			return userResponse;
		}
	}

	
	@Override
	public List<User> getAll() {
		List<User> entity=userRepo.findAll();
		System.out.println(requestDto.getName());
		return entity;
	}

	@Override
	public Optional<User> getUser(Long id) {	
		return userRepo.findById(id);
	}

}
