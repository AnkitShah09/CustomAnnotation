package com.example.service;

import java.util.List;

import com.example.DTO.UserDTO;

public interface CustomAnnotationService {
	public List<String> getValidationResult(UserDTO userDTO);
}
