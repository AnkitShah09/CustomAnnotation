package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.DTO.UserDTO;
import com.example.service.CustomAnnotationService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	CustomAnnotationService customAnnotationService;

	@PostMapping
	public ResponseEntity<Object> addUser(@RequestBody UserDTO userDTO) {
		List<String> validationResult = customAnnotationService.getValidationResult(userDTO);
		if (validationResult.size() > 0)
			return new ResponseEntity<>(validationResult, HttpStatus.BAD_REQUEST);
		// Add user Logic here
		return new ResponseEntity<>("User successfully added !!!", HttpStatus.OK);
	}
}
