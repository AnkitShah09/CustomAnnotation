package com.example.service;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.DTO.UserDTO;
import com.example.customAnnotation.CustomPatternMatcher;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@Service
public class CustomAnnotationServiceImpl implements CustomAnnotationService {

	@Autowired
	Gson gson;

	@Override
	public List<String> getValidationResult(UserDTO userDTO) {

		String jsonObjectString = gson.toJson(userDTO);
		JsonObject userDTOObject = gson.fromJson(jsonObjectString, JsonObject.class);

		List<String> errors = new ArrayList<String>();

		// First We'll retrieve all the fields.
		Field[] declaredFields = UserDTO.class.getDeclaredFields();

		// Then we'll take every single field and check if that field contains
		// CustomPatternMathcer Annotation or not
		// If yes then we'll process
		// If no then we won't do anything.
		for (Field singleField : declaredFields) {
			if (singleField.isAnnotationPresent(CustomPatternMatcher.class)) {
				String regex = singleField.getAnnotation(CustomPatternMatcher.class).value();
				String fieldName = singleField.getName();
				String objectValue = userDTOObject.get(fieldName).getAsString();
				if (!objectValue.matches(regex))
					errors.add("Field ->" + objectValue + "<- is not according to the regular expression.");
			}
		}
		return errors;
	}
}
