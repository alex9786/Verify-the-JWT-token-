package com.example.authentication.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authentication.Dto.LoginDto;
import com.example.authentication.Dto.RequestDto;
import com.example.authentication.Dto.SignUpDto;
import com.example.authentication.common.JwtToken;
import com.example.authentication.common.UserResponse;
import com.example.authentication.entity.User;
import com.example.authentication.service.UserService;

@RestController
@RequestMapping("/rest")
public class UserController {
	
	@Autowired
	UserService  userService;
	
	@Autowired
	 JwtToken jwtToken;
	
	@PostMapping("/signUp")
	public ResponseEntity<UserResponse> signUp(@RequestBody SignUpDto signDto) {
		UserResponse userResponse = userService.signUp(signDto);
		return ResponseEntity.status(userResponse.getStatus()).body(userResponse);
	}
	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> login(@RequestBody LoginDto logindto){
		UserResponse userResponse =userService.login(logindto);
		return ResponseEntity.status(userResponse.getStatus()).body(userResponse);	
	}

	@GetMapping("/get")
	public ResponseEntity<UserResponse> get(@RequestHeader(value="authorization",defaultValue = "") String auth) throws Exception {
		UserResponse userResponse  = userService.get(auth);
		return ResponseEntity.status(userResponse.getStatus()).body(userResponse);	
	}
	
	
	@GetMapping("/getAll")
	public List<User> getAll(){
		return  userService.getAll();
	}
	
	@GetMapping("/get/{id}")
	public Optional<User> getUser(@PathVariable ("id") Long id){
		return userService.getUser(id);
	}
	
	
	

}
